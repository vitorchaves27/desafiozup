package br.com.estacionamento.service;

import java.text.DateFormatSymbols;
import java.util.*;

import br.com.estacionamento.controllers.response.VeiculosResponseDTO;
import br.com.estacionamento.dto.AnoDTO;
import br.com.estacionamento.dto.AnosFipeDto;
import br.com.estacionamento.dto.MarcaDTO;
import br.com.estacionamento.dto.MarcasFipeDTO;
import br.com.estacionamento.dto.ModelosDTO;
import br.com.estacionamento.dto.ModelosFipeDto;
import br.com.estacionamento.dto.ValorFipeDto;
import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.controllers.response.UsuarioDTO;
import br.com.estacionamento.controllers.request.VeiculosDTO;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.estacionamento.model.Veiculos;
import br.com.estacionamento.repository.VeiculosRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;

@Service
public class VeiculosService {

	@Autowired
	private VeiculosRepository veiculoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FipeService fipeService;

	public UsuarioDTO buscaVeiculosPorCpf(String cpf) {

		UsuarioDTO usuarioResponse = new UsuarioDTO();

		Cliente clienteVeiculo = new Cliente();

		clienteVeiculo = clienteService.findCliente(cpf);

		if (clienteVeiculo.getId() != null) {
			List<Veiculos> resultado = veiculoRepository.findByClienteId(clienteVeiculo.getId());
			usuarioResponse = criandoUsuarioDto(clienteVeiculo, resultado);

		} else {
			throw new ServiceException("Cliente não Encontrado");
		}
		return usuarioResponse;

	}

	public Veiculos incluirVeiculos(VeiculosDTO veiculos) {
		Veiculos resultado = new Veiculos();
		Cliente clienteVeiculo = new Cliente();

		if (veiculos.getMarca().isEmpty() || veiculos.getModelo().isEmpty() || veiculos.getAno().isEmpty() || veiculos.getCpf().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Os campos Marca, Modelo e Ano devem ser preenchidos");
		}

		clienteVeiculo = clienteService.findCliente(veiculos.getCpf());

		if (clienteVeiculo.getId() != null && validaDadosFipe(veiculos)) {

			resultado = saveVeiculos(veiculos, clienteVeiculo.getId());

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel finalizar o cadastro!! ");
		}

		return resultado;
	}

	private boolean validaDadosFipe(VeiculosDTO veiculos) {

		String codigoMarca = "";
		String codigoModelo = "";
		String codigoAno = "";
		MarcasFipeDTO marcas = fipeService.getMarcaFipe();
		for(MarcaDTO item : marcas.getMarcas()){
			if(item.getNome().equalsIgnoreCase(veiculos.getMarca())){
				codigoMarca = item.getCodigo();
			}
		}
		if(codigoMarca.isBlank()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Essa marca não foi encontrado na Tabela FIPE");
		}


		ModelosFipeDto modelos = fipeService.getModeloFipe(codigoMarca);
		for(ModelosDTO item : modelos.getModelos()){
			if(item.getNome().equalsIgnoreCase(veiculos.getModelo())){
				codigoModelo = item.getCodigo();
			}
		}
		if(codigoModelo.isBlank()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse modelo não foi encontrado na Tabela FIPE");
		}

		AnosFipeDto anos = fipeService.getAnoFipe(codigoMarca, codigoModelo);
		for(AnoDTO item : anos.getAnos()){
			if(item.getNome().split(" ")[0].equalsIgnoreCase(veiculos.getAno())){
				codigoAno = item.getCodigo();
			}
		}
		if(codigoAno.isBlank()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Esse ano não foi encontrado na tabela FIPE");
		}

		ValorFipeDto valorFipe = fipeService.getValorFipe(codigoMarca,codigoModelo,codigoAno);
		if(!valorFipe.getValor().isBlank()){
			veiculos.setValorFipe(valorFipe.getValor());
		}else{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Verifique os dados cadastrados");
		}

		return true;
	}

	private UsuarioDTO criandoUsuarioDto(Cliente clienteVeiculo, List<Veiculos> resultado) {
		UsuarioDTO usuarioResponse = new UsuarioDTO();
		usuarioResponse.setCpf(clienteVeiculo.getCpf());
		usuarioResponse.setDataNascimento(clienteVeiculo.getDataNascimento());
		usuarioResponse.setEmail(clienteVeiculo.getEmail());
		usuarioResponse.setNome(clienteVeiculo.getNome());
		usuarioResponse.setVeiculos(criaListaVeiculos(resultado));

		return usuarioResponse;
	}

	private List<VeiculosResponseDTO> criaListaVeiculos(List<Veiculos> resultado) {
		List<VeiculosResponseDTO> veiculos = new ArrayList<>();

		for(Veiculos item: resultado){
			VeiculosResponseDTO veiculoNovo = new VeiculosResponseDTO();
			veiculoNovo.setAno(item.getAno());
			veiculoNovo.setMarca(item.getMarca());
			veiculoNovo.setModelo(item.getModelo());
			veiculoNovo.setValor(item.getValor());
			veiculoNovo.setRodizioAtivo(rodizioDia(item.getAno()));
			veiculos.add(veiculoNovo);
		}
		return veiculos;
	}

	private Veiculos saveVeiculos(VeiculosDTO veiculos, Long clienteid) {
		Veiculos veiculoNovo = new Veiculos();
	try{
		veiculoNovo.setMarca(veiculos.getMarca());
		veiculoNovo.setModelo(veiculos.getModelo());
		veiculoNovo.setAno(veiculos.getAno());
		veiculoNovo.setClienteId(clienteid);
		veiculoNovo.setValor(veiculos.getValorFipe());

		return veiculoRepository.saveAndFlush(veiculoNovo);
	 }catch (Exception e){
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cadastro incorreto");
	 }
	}

	private Boolean rodizioDia(String ano) {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);

		String ultimoDigito = ano.substring(ano.length() - 1);


		switch (ultimoDigito){
			case "0":
				if(weekDay(calendar).equalsIgnoreCase("segunda-feira")){
					return true;
				}else{
					return false;
				}
			case "1":
				if(weekDay(calendar).equalsIgnoreCase("segunda-feira")){
					return true;
				}else{
					return false;
				}
			case "2":
				if(weekDay(calendar).equalsIgnoreCase("terça-feira")){
					return true;
				}else{
					return false;
				}
			case "3":
				if(weekDay(calendar).equalsIgnoreCase("terça-feira")){
				return true;
				}else{
					 return false;
			}
			case "4":
				if(weekDay(calendar).equalsIgnoreCase("quarta-feira")){
					return true;
				}else{
					return false;
				}
			case "5":
				if(weekDay(calendar).equalsIgnoreCase("quarta-feira")){
					return true;
				}else{
					return false;
				}
			case "6":
				if(weekDay(calendar).equalsIgnoreCase("quinta-feira")){
					return true;
				}else{
					return false;
				}
			case "7":
				if(weekDay(calendar).equalsIgnoreCase("quinta-feira")){
					return true;
				}else{
					return false;
				}
			case "8":
				if(weekDay(calendar).equalsIgnoreCase("sexta-feira")){
					return true;
				}else{
					return false;
				}
			case "9":
				if(weekDay(calendar).equalsIgnoreCase("sexta-feira")){
					return true;
				}else{
					return false;
				}

				default:
				 return false;
		}
	}

	private String weekDay(Calendar cal) {
		return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
	}

}