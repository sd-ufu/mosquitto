package temperature;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublish {
  public static void main(String[] args) {
    String topic = "sensor/temperature/10";
    int qos = 2;
    String broker = "tcp://localhost:1883";
    String clientId = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient client = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: " + broker);

      client.connect(connOpts);
      System.out.println("Connected");

      try {
        while (true) {
          int temperature = Temperature.getRandomNumber();
          MqttMessage message = new MqttMessage(Integer.toString(temperature).getBytes());
          message.setQos(qos);

          client.publish(topic, message);
          System.out.println("Message published: " + temperature);

          Thread.sleep(1000);
        }
      } catch (InterruptedException ex) {
        client.disconnect();

        System.out.println("Disconnected");
        System.exit(0);
      }
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }
}
