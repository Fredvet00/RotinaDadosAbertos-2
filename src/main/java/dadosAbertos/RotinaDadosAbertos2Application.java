package dadosAbertos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages= "dadosAbertos")
@EntityScan("dadosAbertos.entidades")
public class RotinaDadosAbertos2Application {

	public static void main(String[] args) {
		SpringApplication.run(RotinaDadosAbertos2Application.class, args);

	}
}