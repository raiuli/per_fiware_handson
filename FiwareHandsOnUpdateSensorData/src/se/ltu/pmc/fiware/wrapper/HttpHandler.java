package se.ltu.pmc.fiware.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class HttpHandler {
	
	String method="POST"; 
	String contentType="application/json";
	//String contentType="application/xml";
	private final static int CONNECTION_TIMEOUT = 3000;
	public String sendrequest(String urls, String request) throws IOException{
		InputStream is = null;
		OutputStream os = null;
		String resp = null;
		//URL url = new URL(urls);
		//URL connectionURL = new URL(url + resource);
		URL connectionURL = new URL(urls);
		System.out.println("Connecting to: " + connectionURL.toString());

		// initialize and configure the connection
		HttpURLConnection connection = (HttpURLConnection) connectionURL.openConnection();

		// enable both input and output for this connection
		connection.setDoInput(true);
		connection.setDoOutput(true);

		// configure other things
		connection.setInstanceFollowRedirects(false);
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Accept",contentType);

		// set connection timeout
		connection.setConnectTimeout(CONNECTION_TIMEOUT);

		// set the request method to be used by the connection
		connection.setRequestMethod(method);
		// get outputstream and send data
		os = connection.getOutputStream();
		os.write(request.getBytes(Charset.forName("UTF-8")));
		//System.out.println("------------->Request = "+request);
		// send Message
		os.flush();
		// close connection again
		os.close();
		// now it is time to receive the response
		// get input stream from the connection
		is = connection.getInputStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(is, writer, "UTF-8");
		resp = writer.toString();

		is.close();

		//System.out.println("<-------------Response = "+resp);
		return resp;
		
	}
}
