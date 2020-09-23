package dadosAbertos.Controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import dadosAbertos.Service.Service;

@Configuration
@EnableScheduling
public class Controller {
	@Value("${intervalorepeticao}") //ajustando
	public String intervalorepeticao;
	@Value("${endpoints}")
	public  String[] endpoints;
	@Value("${urlApi}")
	public  String urlApi;
		

	
	 Service service = new Service();
	@Scheduled(fixedDelayString = "200000")
	
	public  void executarChecagem() throws IOException {
		
	//	service.checarStatusHttp(urlApi,endpoints);
		
	}

}
