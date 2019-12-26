package com.suresh.lightcontroller;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTClient {

    private static final String TAG = "MQTTClient";
    private String mqttBroker = "tcp://test.mosquitto.org:1883";
    private String mqttTopic = "codifythings/lightcontrol";
    private String deviceId = "androidClient1";
    private String messageContent = "SWITCH";

    public void publishToMQTT() throws MqttException {
        // Request clean session in the connection options.
        Log.i(TAG, "Setting Connection Options");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);



        // Attempt a connection to MQTT broker using the values
        // of connection variables.
        Log.i(TAG, "Creating New Client");
        MqttClient client = new MqttClient(mqttBroker, deviceId,
                new MemoryPersistence());
        client.connect(options);



        // Publish message to topic
        Log.i(TAG, "Publishing to Topic");
        MqttMessage mqttMessage =
                new MqttMessage(messageContent.getBytes());
        mqttMessage.setQos(2);
        client.publish(mqttTopic, mqttMessage);
        Log.i(TAG, "Publishing Complete");



        Log.i(TAG, "Disconnecting from MQTT");
        client.disconnect();
    }

}
