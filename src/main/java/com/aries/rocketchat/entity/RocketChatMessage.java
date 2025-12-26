package com.aries.rocketchat.entity;

import com.aries.extension.data.EventData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Rocket.Chat Message class
 */

public class RocketChatMessage extends JSONObject{
	
	private EventData event;
	private RocketChatProp prop;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public enum EventLevel {
		NORMAL(":information_desk_person:","#497eff"), WARNING(":warning:","#ffdd00"), FATAL(":rotating_light:","#ff384d");

		private final String iconEmoji;
		private final String color;

		EventLevel(String iconEmoji, String color) {
			this.iconEmoji = iconEmoji;
			this.color = color;
		}

		public String getIconEmoji() {
			return iconEmoji;
		}

		public String getColor() {
			return color;
		}
	}

	private EventLevel level;

	public RocketChatMessage(RocketChatProp prop, EventData event) {
		//event.errorType, event.eventLevel
		this.prop = prop;
		this.event = event;
		this.level = EventLevel.valueOf(event.eventLevel);

		this.addDefaultInfo();
		this.addAttachments();
	}

	private void addDefaultInfo() {
		this.put("username", getProp().getUserName());
		this.put("text", this.buildText());
		this.put("icon_emoji", level.getIconEmoji());
	}

	private void addAttachments() {
		JSONArray attachments = new JSONArray();
		JSONObject attachment = new JSONObject();

		attachment.put("title", this.event.errorType);
		attachment.put("color", level.getColor());
		/* if ("FATAL".equals(this.event.eventLevel))
			attachment.put("color", "#ff384d");
		else if ("WARNING".equals(this.event.eventLevel))
			attachment.put("color", "#ffdd00");
		else
			attachment.put("color", "#497eff"); */

		// Add fields
		attachment.put("fields", this.buildFields());
	
		// Add action button (Rocket.Chat format)
		if (this.prop.getShareUrl() != null && this.event.txid != 0) {
			attachment.put("actions", this.buildActions());
		}

		attachments.put(attachment);
		this.put("attachments", attachments);
	}

	private JSONArray buildActions() {
		JSONArray actions = new JSONArray();

		JSONObject button = new JSONObject();
		button.put("type", "button");
		button.put("text", this.prop.getButtonText());
		button.put("url", this.buildXViewLink());

		actions.put(button);

		return actions;
	}

	private JSONArray buildFields() {
		JSONArray fields = new JSONArray();

		String[] fieldTitles = {
			"Domain ID", "Domain Name",
			"Instance Name", "Transaction ID",
			"Service Name", "Error Type",
			"Error Level", "Error Time"
		};

		Object[] fieldValues = {
			safeToString(event.domainId),
			safeToString(event.domainName),
			safeToString(event.instanceName),
			safeToString(event.txid),
			safeToString(event.serviceName),
			safeToString(event.errorType),
			safeToString(event.eventLevel),
			this.sdf.format(new Date(event.time))
		};

		boolean[] fieldShorts = {
			true, true, false, true, true, false, true, true
		};

		for (int i = 0; i < fieldTitles.length; i++) {
			JSONObject field = new JSONObject();
			field.put("title", fieldTitles[i]);
			field.put("value", fieldValues[i]);
			field.put("short", fieldShorts[i]);
			fields.put(field);
		}

		return fields;
	}

	private String safeToString(Object o) {
		return o == null ? "" : Objects.toString(o);
	}

	private String buildXViewLink() {
		String popupUrl = "/popup/xviewAnalysisV2?domainId=" + this.event.domainId +
				"&transactionId=" + this.event.txid + "&searchTime=" + this.event.time;
		return this.prop.getShareUrl() + popupUrl + "&redirect=" + encodeURIComponent(popupUrl);
	}

	private String buildText() {
		StringBuilder text = new StringBuilder();
		text.append(String.format("The following event [%s] was caught by JENNIFER. %n",  this.event.errorType));
		text.append("Here are some additional details\n");
		
		return text.toString();
	}

	public static String encodeURIComponent(String s)
	{
		String result = null;

		try
		{
			result = URLEncoder.encode(s, "UTF-8")
					.replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(")
					.replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		}

		// This exception should never occur.
		catch (UnsupportedEncodingException e)
		{
			result = s;
		}

		return result;
	}

	public RocketChatProp getProp() {
		return prop;
	}
}
