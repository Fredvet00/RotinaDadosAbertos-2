package dadosAbertos.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import dadosAbertos.utils.DadosAbertosConnection;
import dadosAbertos.utils.JavaMailApp;

public class Service {
	DadosAbertosConnection dadosAbertosConnection = new DadosAbertosConnection();
	JavaMailApp javaMail = new JavaMailApp();
	@Value("${responseTimeLimit}") //resolver , valor não está sendo passado adequadamento.
	//double responseTimeLimit;
	
	public void checarStatusHttp(String urlApi, String[] endpoints) throws IOException {
		// return Integer.toString(conn.getResponseCode());
		for (int i = 0; i < endpoints.length; i++) {
			long initTime = System.currentTimeMillis();
			int response = dadosAbertosConnection.doRequest(urlApi, endpoints[i]).getResponseCode();
			long finalTime = System.currentTimeMillis();
			double responseTime = (finalTime - initTime) / 1000d;
			if ((response != 200) || (responseTime >= 20)) {

				if (response != 200) {
					response = testAgain200(response, endpoints[i].toString(), urlApi);
					if (response != 200) {
						String emailerro = ("Erro na requisição nº:" + i + ",referente ao endpoint: [" + endpoints[i]
								+ "] com status http: [" + response + "]").toString();
						System.out.println(emailerro); //aqui javaMail.enviarMensagem(emailerro);
						continue;
					} else
						System.out.println("Recuperada de falso erro em HTTP status da Requisição nº:" + i + ", referente ao Endpoint:["+ endpoints[i]+"]");
						continue; //armazenado em log

				}

				if (responseTime >=20 ) {
					responseTime = testAgainResponseTime(responseTime, endpoints[i], urlApi);
					if (responseTime >= 20) {
						String emailerro = ("ERROR na requisição nº:" + i + ",referente ao endpoint: [" + endpoints[i]
								+ "] com response time em alerta  com tempo de: [" + responseTime + "] segundos").toString();
						System.out.println(emailerro);//aqui javaMail.enviarMensagem(emailerro);
						continue;

					} else
						System.out.println("Recuperada de falso erro em responseTime da Requisição nº:" + i + ", referente ao Endpoint:["+ endpoints[i]+"]");
						continue; //armazenado em log
				}

				
			} else
				System.out.println("Requisição nº" + i + ",referente ao endpoint: [" + endpoints[i]
						+ "] feita com sucesso, status http: [" + response + "] e tempo com o tempo de resposta de:["
						+ responseTime + "] segundos");
		}

	}

	public int testAgain200(int response, String valorendpoint, String urlApi) throws IOException {
		int testTimes = 0;
		while ((response != 200) && (testTimes <= 10)) {

			response = dadosAbertosConnection.doRequest(urlApi, valorendpoint).getResponseCode();
			testTimes = testTimes+1;
		}
		return response;
	}

	public double testAgainResponseTime(double responseTime, String valorendpoint, String urlApi) throws IOException {
		int testTimes = 0;
		while ((responseTime >= 20) && (testTimes <= 10)) {
			long initTime = System.currentTimeMillis();
			int response = dadosAbertosConnection.doRequest(urlApi, valorendpoint).getResponseCode();
			long finalTime = System.currentTimeMillis();
			responseTime = (finalTime - initTime) / 1000d;
			testTimes=testTimes+1;
		}
		return responseTime;
	}
}
