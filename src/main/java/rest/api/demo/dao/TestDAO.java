package rest.api.demo.dao;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class TestDAO {
	
	String BASE_URL = "http://localhost:8080/company/employees";
	String CONTENT_TYPE = "application/json";
	
	public HttpURLConnection save() throws Exception{
	
		String METHOD = "POST";
		String BODY = "{\"name\":\"Teste App\", \"designation\":\"Senior Test\",\"expertise\":\"REST API Spring test\"}";

		URL obj = new URL(BASE_URL);
		
		System.out.println("Sending request to: " + BASE_URL);
		
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		
		connection.setRequestMethod(METHOD);
		connection.setRequestProperty("Content-Type", CONTENT_TYPE);

		/*
		 * Quando se pretende usar metodos diferentes de GET poe-se false
		 * */
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		
		/*
		 * Quando se pretende escrever algo na db envia-se o corpo em json
		 * */
		wr.writeBytes(BODY);
		wr.flush();
		wr.close();
		
		return connection;
		
	}
	
	public HttpURLConnection findAll() throws Exception{
		
		URL obj = new URL(BASE_URL);
		
		System.out.println("Sending request to: " + BASE_URL);
		
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		
		return connection;
	}

}
