package dadosAbertos.Service;

import java.io.IOException;

import dadosAbertos.utils.DadosAbertosConnection;
import dadosAbertos.utils.JavaMailApp;

public class Service {
	DadosAbertosConnection dadosAbertosConnection = new DadosAbertosConnection();
	JavaMailApp javaMail = new JavaMailApp();

	public void checarStatusHttp(String urlApi, String[] endpoints) throws IOException {
		// return Integer.toString(conn.getResponseCode());
		for (int i = 0; i < endpoints.length; i++) {
			String response = Integer.toString(dadosAbertosConnection.doRequest(urlApi, endpoints[i]).getResponseCode());
			if (!response.equals("200")) {
				String emailerro = ("Erro na requisição nº:" + i + ",referente ao endpoint: [" + endpoints[i]
						+ "] com status http: [" + response + "]").toString();
				javaMail.enviarMensagem(emailerro);

			}

			else
				System.out.println("Requisição nº" + i + " ,referente ao endpoint: [" + endpoints[i]
						+ "] feita com sucesso, status http: [" + response + "]");
		}

	}
}
