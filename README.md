# mosquitto

## Comands to run project

### Compile java files
javac -cp jars/org.eclipse.paho.client.mqttv3-1.2.6-20200715.040602-1.jar -d . *.java

### Start publish
java -cp jars/org.eclipse.paho.client.mqttv3-1.2.6-20200715.040602-1.jar:. temperature.MqttPublish

### Start subscriber
java -cp jars/org.eclipse.paho.client.mqttv3-1.2.6-20200715.040602-1.jar:. temperature.MqttSubscriber
