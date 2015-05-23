package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

public class HttpClientHelper {

	public HttpResponse executeGetRequest(URL url, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet httpget = httpGetRequest(url, params);
		HttpResponse response = HttpClient.getClient().execute(httpget);
		return response;

	}

	public HttpResponse executeGetRequestWithTimeout(URL url,
			Map<String, String> params, int timeout) throws URISyntaxException,
			ClientProtocolException, IOException {
		HttpGet httpget = httpGetRequest(url, params);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build();
		httpget.setConfig(requestConfig);
		HttpResponse response = HttpClient.getClient().execute(httpget);

		return response;

	}

	private HttpGet httpGetRequest(URL url, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(url.getProtocol()).setHost(url.getHost())
				.setPath(url.getPath());
		for (Entry<String, String> entry : params.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);
		return httpget;
	}

}
