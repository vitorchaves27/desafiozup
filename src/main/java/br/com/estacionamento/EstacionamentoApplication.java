package br.com.estacionamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstacionamentoApplication {

	public static void main(String[] args) {
		Logger LOG = LoggerFactory.getLogger("br.com.estacionamento");

		SpringApplication.run(EstacionamentoApplication.class, args);

		LOG.info(
			"| \n" +
				" |------------------------------------------------------------ \n" +
				" |	 Application SgilApplication is running! Access URLs: \n" +
				" |   Local:      http://localhost:8080/ \n" +
				" |------------------------------------------------------------"
		);
	}

}
