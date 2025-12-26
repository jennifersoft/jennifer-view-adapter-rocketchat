package com.aries.rocketchat;

import java.util.ArrayList;
import com.aries.extension.data.EventData;
import com.aries.extension.handler.EventHandler;
import com.aries.extension.util.LogUtil;
import com.aries.rocketchat.entity.RocketChatProp;
import com.aries.rocketchat.util.ConfUtil;
import com.aries.rocketchat.util.RocketChatClient;
import com.aries.rocketchat.entity.RocketChatMessage;

/**
 * 
 * The main logic for the extension
 */
public class RocketChatAdapter implements EventHandler{

	// Adapter delegates message formatting to RocketChatMessage (Rocket.Chat format)
	public void on(EventData[] eventData) {
		RocketChatProp rocketChatProperties = ConfUtil.getRocketChatProperties();

		for (EventData event : eventData) {
			RocketChatMessage rcMessage = new RocketChatMessage(rocketChatProperties, event);
			String result = new RocketChatClient(rcMessage).push().trim();
			if(!result.trim().equalsIgnoreCase("ok"))
				LogUtil.error("Failed to push message to Rocket.Chat");
		}
	}

	public static void main(String[] args) {
		EventData event = new EventData((short) 1000, 
										new ArrayList<String>(), 
										"Main", 
										System.currentTimeMillis(), 
										1000, 
										"Groupware", 
										"", 
										"SERVICE_EXCEPTION", 
										"", 
										"FATAL", 
										"", 
										-1, 
										"SYSTEM", 
										"", 
										"/service.jsp", 
										-123123123, 
										"", 
										null);
		new RocketChatAdapter().on(new EventData[] { event });
	}
}
