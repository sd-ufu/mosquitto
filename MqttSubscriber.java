package temperature;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber implements MqttCallback  {
	private static final String brokerUrl ="tcp://localhost:1883";
	private static final String clientId = "clientId";
	private static final String topic = "sensor/temperature/10";

	public static void main(String[] args) {
		System.out.println("Subscriber running");
		new MqttSubscriber().subscribe(topic);
	}

	public void subscribe(String topic) {
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();

			connOpts.setCleanSession(true);

			System.out.println("Connecting to broker: " + brokerUrl);

			sampleClient.connect(connOpts);
			System.out.println("Connected");

			sampleClient.setCallback(this);
			sampleClient.subscribe(topic);

			System.out.println("Subscribed");
			System.out.println("Listening");
		} catch (MqttException me) {
			System.out.println(me);
		}
	}

	public void connectionLost(Throwable arg0) {
    System.out.println("CONNECTION LOST");
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("TOPIC:" + topic);
		System.out.println("TEMPERATURE: " + message.toString());
		System.out.println("-------------------------------------------------");
	}
}
