package test.httpclient;

import httpclient.HttpClientHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import parser.dto.timezone.TimeZone;
import response.parsers.JsonParser;
import timezone.data.test.TimeZoneServiceData;

public class Test1 {

	HttpClientHelper helper;

	@BeforeTest
	public void setUp() {
		helper = new HttpClientHelper();
	}

	@Test
	public void statusLineTest() throws ClientProtocolException, IOException,
			URISyntaxException {
		URL testUrl = new URL("https", "maps.googleapis.com",
				"/maps/api/timezone/json");
		Map<String, String> params = new HashMap<String, String>();
		params.put("location", "39.6034810,-119.6822510");
		params.put("timestamp", "1331766000");
		params.put("language", "es");

		HttpResponse response = helper.executeGetRequest(testUrl, params);
		System.out
				.println("------------------------------------------------------------------");
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK",
				"Reason phrase");
		Assert.assertEquals(response.getStatusLine().getProtocolVersion()
				.toString(), "HTTP/1.1", "Protocol version");
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200,
				"Status Code");
	}
	@Test
	public void responseBodyTest() throws IOException, URISyntaxException {
		URL testUrl = new URL("https", "maps.googleapis.com",
				"/maps/api/timezone/json");
		Map<String, String> params = new HashMap<String, String>();
		params.put("location", "39.6034810,-119.6822510");
		params.put("timestamp", "1331766000");
		params.put("language", "es");

		HttpResponse response = helper.executeGetRequest(testUrl, params);
		JsonParser jParser = new JsonParser();
		TimeZone timeZone = jParser.readJsonToObject(TimeZone.class, response.getEntity().getContent());
		Assert.assertEquals(timeZone.getDstOffset(), 3600.0, "Parameter DstOffset");
		Assert.assertEquals(timeZone.getRawOffset(), -28800.0, "Parameter RawOffset");
		Assert.assertEquals(timeZone.getStatus(), "OK", "Parameter Status");
		Assert.assertEquals(timeZone.getTimeZoneId(), "America/Los_Angeles", "Parameter timeZoneId");
	}
	@Test
	public void responseBodyWithDBTest() throws IOException, URISyntaxException{
		URL testUrl = new URL("https", "maps.googleapis.com",
				"/maps/api/timezone/json");
		Map<String, String> params = new HashMap<String, String>();
		params.put("location", "39.6034810,-119.6822510");
		params.put("timestamp", "1331766000");
		params.put("language", "es");

		HttpResponse response = helper.executeGetRequest(testUrl, params);
		JsonParser jParser = new JsonParser();
		TimeZone timeZone = jParser.readJsonToObject(TimeZone.class, response.getEntity().getContent());
		TimeZoneServiceData db = new TimeZoneServiceData();
		Assert.assertEquals(timeZone.getDstOffset(), db.prepareTimeZoneObjects().getDstOffset(), "Parameter DstOffset");
		
	}
}
