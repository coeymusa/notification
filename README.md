Hue Notification Project

Running on port : 7077
Webhook: https://zapier.com/app/home

A quick project to trigger hue lights based on a notification from an email.

Using Zapier to poll an email and POST the contents of any new emails. The post is forwarded to my local machine running the application.
This is done via ngrok, which forwards the request POST to the application.

The email is then read and parsed for any trigger words e.g. "American Horror Story".

When trigger is received it will GET all the lights.
and their current state, and then perform some modification e.g. color -> RED and post the lights back to be changed.

Further implementation for different effects, triggers and configurablity to be added.

Requires local installation of MongoDB-
https://docs.mongodb.com/v3.2/tutorial/install-mongodb-on-windows/

To add Trigger: POST http://localhost:7077/addtrigger

{
    "colour": [0.70, 0.25],
    "name": "American Horror Story",
    "effect": "NONE"
}
 Colours can be found at https://www.developers.meethue.com/documentation/core-concepts - replacing double values with coordinates desired
