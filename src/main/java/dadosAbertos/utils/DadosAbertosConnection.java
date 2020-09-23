package dadosAbertos.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class DadosAbertosConnection {
	
	
	public HttpURLConnection doRequest(String urlApi, String endpoints) throws IOException {
		URL url = new URL(urlApi + endpoints);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		try {
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			return conn;
			//return Integer.toString(conn.getResponseCode());

		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		} finally {
			conn.disconnect();
		}
			return null;
	}

}
