package dadosAbertos.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import dadosAbertos.utils.DadosAbertosConnection;

public class Service {
	DadosAbertosConnection dadosAbertosConnection = new DadosAbertosConnection();
	

	@Value("${responseTimeLimit}") // resolver , valor não está sendo passado adequadamento.
	// double responseTimeLimit;

	public void checarStatusHttp(String urlApi, String[] endpoints) throws IOException {
		// return Integer.toString(conn.getResponseCode());
		for (int i = 0; i < endpoints.length; i++) {
			long initTime = System.currentTimeMillis();
			int response = dadosAbertosConnection.doRequest(urlApi, endpoints[i]).getResponseCode();
			long finalTime = System.currentTimeMillis();
			double responseTime = (finalTime - initTime) / 1000d;
			if ((response != 200) || (responseTime >= 20)) {
				
				ServiceSecondTask secTask = new ServiceSecondTask();
				secTask.setEndpoints(endpoints[i]);
				secTask.setI(i);
				secTask.setResponseTime(responseTime);
				secTask.setResponse(response);
				Thread thread = new Thread(secTask);
				thread.start();
			//	System.out.println(metodosegundatask);

			} else
				System.out.println("Requisição nº" + i + ",referente ao endpoint: [" + endpoints[i]
						+ "] feita com sucesso, status http: [" + response + "] e tempo com o tempo de resposta de:["
						+ responseTime + "] segundos");}
		}


	

}
