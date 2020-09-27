package dadosAbertos.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import dadosAbertos.utils.DadosAbertosConnection;
import dadosAbertos.utils.JavaMailApp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceSecondTask implements Runnable {
	JavaMailApp javaMail = new JavaMailApp();
	DadosAbertosConnection dadosAbertosConnection = new DadosAbertosConnection();
	int response;
	String endpoints;
	double responseTime;
	int i;
	public String urlApi = "https://dadosabertos.camara.leg.br/api/v2";

	@Override
	public void run() {

		if (response != 200) {
			try {
				response = testAgain200(response, endpoints.toString(), urlApi);
			} catch (IOException e) {
				System.out.println("erro em status http");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (response != 200) {
				String emailerro = ("Erro na requisição nº:" + i + ",referente ao endpoint: [" + endpoints
						+ "] com status http: [" + response + "]").toString();
				javaMail.enviarMensagem(emailerro);	//System.out.println(emailerro); 

			} else
				System.out.println("Recuperada de falso erro em HTTP status da Requisição nº:" + i
						+ ", referente ao Endpoint:[" + endpoints + "]");
			// armazenado em log

		}

		if (responseTime >= 20) {
			try {
				responseTime = testAgainResponseTime(responseTime, endpoints, urlApi);
			} catch (IOException e) {
				System.out.println(" erro em response time");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (responseTime >= 20) {
				String emailerro = ("ERROR na requisição nº:" + i + ",referente ao endpoint: [" + endpoints
						+ "] com response time em alerta  com tempo de: [" + responseTime + "] segundos").toString();
				 javaMail.enviarMensagem(emailerro);//System.out.println(emailerro);

			} else
				System.out.println("Recuperada de falso erro em responseTime da Requisição nº:" + i
						+ ", referente ao Endpoint:[" + endpoints + "]");
			// armazenado em log
		}

	}

	public int testAgain200(int response, String valorendpoint, String urlApi) throws IOException, InterruptedException {
		int testTimes = 0;
		while ((response != 200) && (testTimes <= 10)) {
			response = dadosAbertosConnection.doRequest(urlApi, valorendpoint).getResponseCode();
			System.out.println("Tetetativa nº:[" + testTimes + "/10] confirmando erro de HTTPstatus do endpoint:" + valorendpoint);
			testTimes = testTimes + 1;
			Thread.sleep(20000);
		}
		return response;
	}

	public double testAgainResponseTime(double responseTime, String valorendpoint, String urlApi)
			throws IOException, InterruptedException {
		int testTimes = 0;
		while ((responseTime >= 20) && (testTimes <= 10)) {
			long initTime = System.currentTimeMillis();
			int response = dadosAbertosConnection.doRequest(urlApi, valorendpoint).getResponseCode();
			long finalTime = System.currentTimeMillis();
			responseTime = (finalTime - initTime) / 1000d;
			testTimes = testTimes + 1;
			System.out.println("Tetetativa nº:[" + testTimes + "/10] confirmando erro de ResponseTime do endpoint:" + valorendpoint);
			Thread.sleep(20000);
		}
		return responseTime;
	}

}
