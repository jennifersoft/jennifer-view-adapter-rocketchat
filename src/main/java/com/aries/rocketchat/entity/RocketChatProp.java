package com.aries.rocketchat.entity;

/**
 * Rocket.Chat Configuration and Properties class
 *
 */
public class RocketChatProp {

	/**
	 * Rocket.Chat Incoming Webhook URLs (comma-separated)
	 */
	private String webHookUrls;
	
	/**
	 * The username to be shown when sending message 
	 */
	private String userName;
	
	/**
	 * JENNIFER X-View Share URL Used to open X-View pop-up
	 */
	private String shareUrl;

	/**
	 * View On Xivew Button
	 */
	private String buttonText;
	
	public String getWebHookUrls() {
		return webHookUrls;
	}

	public void setWebHookUrls(String webHookUrls) {
		this.webHookUrls = webHookUrls;
	}
	
	/**
	 * Returns an array of webhook URLs by splitting the comma-separated string
	 */
	public String[] getWebHookUrlArray() {
		if (webHookUrls == null || webHookUrls.trim().isEmpty()) {
			return new String[0];
		}
		return webHookUrls.split(",");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
}
