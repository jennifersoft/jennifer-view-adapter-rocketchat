# Overview
This adapter will send EVENT notification to a Rocket.Chat channel or a Rocket.Chat user.

## Getting started

### 1- Adapter Installation
The first step is to register the adapter: 
1. In JENNIFER Dashboard, go to **Settings** > **SMTP and Adapter (+DB Plan)**.
2. Click the **[+Add]** button at the top right of the screen.
3. Click Adapter and enter the required information for registration.
    * For Classifications, select **EVENT**.
    * For ID, enter **rocketchat**.
    * Click [Select File] and choose the downloaded file(rocketchat-adapter-x.x.x.jar).
    * For Class, enter **com.aries.rocketchat.RocketChatAdapter**.
4. Click the **[Add]** button to add the adapter. 



### 2- Adapter Options

Next step is to add the adapter options. There are only 1 required options that you must configure, the **rocketchat_webhook** option. 
The rest of the options are optional. Refer the table below for the full list of available options for this adapter.


The following table shows the available options for this adapter

| Key                | Required |                      Description                       |  Default Value | 
| ------------------ |:--------:|:------------------------------------------------------:|:--------------:|
| rocketchat_webhook |   YES    |  Set Rocket.Chat Incoming Webhook URLs                 | None           | 
| username           |    NO    |  This will change the "From" username when receiving Rocket.Chat Message  | JENNIFER Notification | 
| share_url          |    NO    | Set JENNIFER Share URL for the X-View pop-up plugin. If the URL value is set, then this adapter will attempt to generate link to view the transactions in X-View and display the link in the Rocket.Chat message    | None                  | 
| button_text        |    NO    | only used if the share_url option is enabled. Customize the Rocket.Chat interactive button text | View On X-View |

**IMPORTANT: In order to use the `share_url` functionality, [XView Pop-up Plugin](https://github.com/jennifersoft/jennifer-view-plugin-xviewpopup) must be configured and enabled.**



