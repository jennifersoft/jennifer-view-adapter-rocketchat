# Overview
This adapter will send EVENT notification to a Rocket.Chat channel or a Rocket.Chat user.

## Getting started

### 1- Adapter Installation
The first step is to register the adapter: 
1. In JENNIFER Dashboard, open the management area and Navigate to  **Extension and Notice** > **Adapter and Plugin**
2. Make sure the **Adapter** tab is selected then click the **[+Add]** button
3. Click on the **Path** text field to display list of available adapters.
4. Select **Event Adapter for Rocket.Chat Messenger]** from drop down list.
4. Click the **Save** button to add the adapter. 




### 2- Adapter Options

Next step is to add the adapter options. There are only 1 required options that you must configure, the **rocketchat_webhook** option. 
The rest of the options are optional. Refer the table below for the full list of available options for this adapter.


The following table shows the available options for this adapter

| Key                | Required |                      Description                       |  Default Value | 
| ------------------ |:--------:|:------------------------------------------------------:|:--------------:|
| rocketchat_webhook |   YES    |  Set Rocket.Chat Incoming Webhook URLs                 | None           | 
| username           |    NO    |  Option: This will change the "From" username when receiving Rocket.Chat Message  | JENNIFER Notification | 
| share_url          |    NO    | Optional: Set JENNIFER Share URL for the X-View pop-up plugin. If the URL value is set, then this adapter will attempt to generate link to view the transactions in X-View and display the link in the Rocket.Chat message    | None                  | 
| button_text        |    NO    | Optional: only used if the share_url option is enabled. Customize the Rocket.Chat interactive button text | View On X-View |

**IMPORTANT: In order to use the `share_url` functionality, [XView Pop-up Plugin](https://github.com/jennifersoft/jennifer-view-plugin-xviewpopup) must be configured and enabled.**



