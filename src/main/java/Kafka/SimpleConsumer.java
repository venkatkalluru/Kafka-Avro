package Kafka;

import Message.EventMessage;
import Message.EventMessageDeserializer;
import Rule.RuleDeserializer;
import Rule.RuleMessage;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.io.PrintStream;

public class SimpleConsumer {
	public SimpleConsumer() {

	}

	private static class MsgConsumer implements Runnable {
		public void run() {
			Properties props = new Properties();
			props.put("bootstrap.servers", "10.204.99.224:9092");
			props.put("group.id", "events");
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.deserializer",
					"org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer",
					"org.apache.kafka.common.serialization.ByteArrayDeserializer");
			KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<String, byte[]>(
					props);
			consumer.subscribe(Arrays.asList("venkat-oggtopic"));

			while (true) {
				ConsumerRecords<String, byte[]> records = consumer.poll(100);
				for (ConsumerRecord<String, byte[]> record : records) {
					// System.out.println("Bytes are " + record.value());
//					 System.out.println("Print Stream data " + new String(record.value()));
					try {
						ArrayList<RuleMessage> rmList = new RuleDeserializer()
								.DeserializeRule(record.value());
					for (Iterator<RuleMessage> iterator = rmList.iterator(); iterator
							.hasNext();) {
						RuleMessage ruleMessage = (RuleMessage) iterator.next();
						System.out.printf(
								"offset = %d, key = %s, value = %s \n",
								record.offset(), record.key(), ruleMessage);
					}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

	}

	private static class SchemaConsumer implements Runnable {
		public void run() {
			Properties props = new Properties();
			props.put("bootstrap.servers", "10.204.99.224:9092");
			props.put("group.id", "events");
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.deserializer",
					"org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer",
					"org.apache.kafka.common.serialization.ByteArrayDeserializer");
			KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<String, byte[]>(
					props);
			consumer.subscribe(Arrays.asList("venkat-oggSchema"));

			while (true) {
				ConsumerRecords<String, byte[]> records = consumer.poll(100);
				for (ConsumerRecord<String, byte[]> record : records) {
					String schema = new String(record.value());
					System.out.println("Schema value is " + schema);
					System.out.println("Schema Hash Code is "
							+ schema.hashCode());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new MsgConsumer());
		t1.start();
		 Thread t2 = new Thread(new SchemaConsumer());
		 t2.start();
	}
}
