package one.digitalInnovation.desafio_gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioGftApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioGftApplication.class, args);
	}
}
