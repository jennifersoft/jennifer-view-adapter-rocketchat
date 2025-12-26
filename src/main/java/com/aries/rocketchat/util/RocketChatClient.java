package com.aries.rocketchat.util;

import com.aries.extension.util.LogUtil;
import com.aries.rocketchat.entity.RocketChatMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Rocket.Chat Client for pushing message to Rocket.Chat
 *
 */
public class RocketChatClient {
	
	/**
	 * Default connection time out value
	 */
	private final int CONNECTION_TIME_OUT	= 1000;
	
	/**
	 * Default encoding value
	 */
	private final String ENCODING			= "UTF-8";
	
	/**
	 * RocketChatMessage instance
	 */
	private RocketChatMessage rocketChatMessage;

	/**
	 * Default constructor
	 * @param message RocketChatMessage object
	 */
	public RocketChatClient(RocketChatMessage message) {
		this.rocketChatMessage = message;
	}
	
	/**
	 * Push message to Rocket.Chat using simple URLConnection
	 * @return Return either "ok" if message was sent, or null if message was not sent or an exception occured.
	 */
	public String push() {
		String[] webhookUrls = rocketChatMessage.getProp().getWebHookUrlArray();
		if (webhookUrls.length == 0) {
			LogUtil.error("No webhook URLs configured");
			return "";
		}
		
		boolean allSuccess = true;
		
		for (String webhookUrl : webhookUrls) {
			String trimmedUrl = webhookUrl.trim();
			if (trimmedUrl.isEmpty()) {
				continue;
			}
			
			String response = pushToSingleWebhook(trimmedUrl);
			if (response.isEmpty()) {
				allSuccess = false;
			}
		}
		
		return allSuccess ? "ok" : "";
	}
	
	/**
	 * Push message to a single webhook URL
	 * @param webhookUrl The webhook URL to send message to
	 * @return Response string or empty string on error
	 */
	private String pushToSingleWebhook(String webhookUrl) {
	    HttpURLConnection connection = null;
	    try {
	        byte[] payload = rocketChatMessage.toString().getBytes(StandardCharsets.UTF_8);

	        URL url = new URL(webhookUrl);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setConnectTimeout(CONNECTION_TIME_OUT);
	        connection.setReadTimeout(CONNECTION_TIME_OUT);
	        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
	        connection.setRequestProperty("Content-Length", String.valueOf(payload.length));
	        connection.setUseCaches(false);
	        connection.setDoOutput(true);

	        try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
	            out.write(payload);
	            out.flush();
	        }

	        int statusCode = connection.getResponseCode();
	        InputStream in = (statusCode >= 200 && statusCode < 300)
	                ? connection.getInputStream()
	                : connection.getErrorStream();

	        StringBuilder response = new StringBuilder();
	        if (in != null) {
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    response.append(line).append('\n');
	                }
	            }
	        }

	        if (statusCode < 200 || statusCode >= 300) {
	            LogUtil.error(
	                "Rocket.Chat webhook push failed. " +
	                "status=" + statusCode +
	                ", url=" + webhookUrl +
	                ", response=" + response
	            );
	            return "";
	        }

	        return "ok";

	    } catch (Exception ex) {
	        LogUtil.error(
	            "Exception while pushing Rocket.Chat webhook. url=" +
	            webhookUrl + ", reason=" + ex
	        );
	        return "";
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	}
}
