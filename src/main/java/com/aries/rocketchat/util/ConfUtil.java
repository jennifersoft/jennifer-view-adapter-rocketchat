package com.aries.rocketchat.util;

import com.aries.extension.util.PropertyUtil;
import com.aries.rocketchat.entity.RocketChatProp;

/**
 * Load adapter configuration
 */
public class ConfUtil {

	private static final RocketChatProp rocketChatProperties = new RocketChatProp();

	/**
	 * The adapter ID
	 */
	private static final String ADAPTER_ID = "rocketchat";
	
	/**
	 * Get a configuration value using the provided key
	 * @param key configuration key. Set this key value in the adapter configuration menu in JENNIFER client.
	 * @param defaultValue Optional default configuration value
	 * @return String configuration value
	 */
	public static String getValue(String key, String defaultValue){
		return PropertyUtil.getValue(ADAPTER_ID, key, defaultValue);
	}

	/**
	 * Get the Rocket.Chat properties
	 * @return RocketChatProp Rocket.Chat properties
	 */
	public static RocketChatProp getRocketChatProperties() {
		rocketChatProperties.setUserName(getValue("username", "JENNIFER Notification"));
		rocketChatProperties.setWebHookUrls(getValue("rocketchat_webhook", null));
		rocketChatProperties.setShareUrl(getValue("share_url", null));
		rocketChatProperties.setButtonText(getValue("button_text", "View On X-View"));

		return  rocketChatProperties;
	}

	public static RocketChatProp _getRocketChatProperties() {
		rocketChatProperties.setUserName(getValue("username", "JENNIFER Notification"));		
		rocketChatProperties.setWebHookUrls(getValue("rocketchat_webhook", "http://localhost:3000/hooks/6944dc2a8ee6824f436dd6eb/vTEGcHjiP89Y5nFdkLsTAZcWg7vBKT8a8xeFHdjJE7JPmB63"));
		rocketChatProperties.setShareUrl(getValue("share_url", "https://localhost:7900/"));
		rocketChatProperties.setButtonText(getValue("button_text", "View On X-View"));

		return  rocketChatProperties;
	}
}
