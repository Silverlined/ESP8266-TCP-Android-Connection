#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <stdlib.h>

const char* ssid = "ZiggoC6C6A6C";
const char* password = "3YjjjrzteyFk";
//const char* ssid = "AndroidAP_63";
//const char* password = "00000000";

// Create a TCP Server on port 8085
WiFiServer server(5045);
WiFiClient client;
WiFiClient connectedClient;

unsigned long previousMillis = 0;

int sensorValue = 1;
char buf[12];

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  Serial.println("");
  //Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());

  //Start the TCP server
  server.begin();
}

void loop() {
  client = server.available();
  if (client) {
    while (client.connected()) {
      Serial.println("Connected to client");
      //    if (client.available() > 0) {
      //      // Read incoming message
      //      char inChar = client.read();
      //      // Send back something to the clinet
      //      server.write(inChar);
      //      // Echo input on Serial monitor
      //      Serial.write(inChar);
      if (millis() - previousMillis > 3000) {
        sensorValue = analogRead(A0);
        previousMillis = millis();
        client.write(itoa(sensorValue, buf, 10));
        client.write("\n");                           //new line marks the end of this reading
      }
      delay(1500);
    }
  }
}


