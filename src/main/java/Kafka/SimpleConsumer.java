package Kafka;

import Rule.RuleDeserializer;
import Rule.RuleMessage;

import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class SimpleConsumer {
	private static Map<Integer, String> tableToSchemaMap = new HashMap<>();
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
					 System.out.println("Print Stream data " + new String(record.value()));
					try {
						ArrayList<RuleMessage> rmList = new RuleDeserializer()
								.DeserializeRule(record.value());
						for (Iterator<RuleMessage> iterator = rmList.iterator(); iterator
								.hasNext();) {
							RuleMessage ruleMessage = (RuleMessage) iterator
									.next();
//							System.out.printf(
//									"offset = %d, key = %s, value = %s \n",
//									record.offset(), record.key(), ruleMessage);
							if (tableToSchemaMap.containsKey(ruleMessage.getSchemaHash())) {
								String schemaStr = tableToSchemaMap.get(ruleMessage.getSchemaHash());
								Schema schema = new Schema.Parser().parse(schemaStr);
								
								DatumReader<RuleMessage> reader = new SpecificDatumReader<>(schema);
								Decoder decoder = null;
								try{
									decoder = DecoderFactory.get().binaryDecoder(ruleMessage.getPayload().array(), null);
								   
									Object msg = reader.read(null, decoder);
									System.out.println("Decoded CDC data is " + msg);
									
								} catch(EOFException exception){
								//    exception.printStackTrace();
								} catch(IOException exception){
									exception.printStackTrace();
								}
							}
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
		
		public SchemaConsumer() {
		}
		
		public void run() {
			Properties props = new Properties();
			props.put("bootstrap.servers", "10.204.99.224:9092");
//			props.put("group.id", "events");
			props.put("group.id", UUID.randomUUID().toString());
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.deserializer",
					"org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer",
					"org.apache.kafka.common.serialization.ByteArrayDeserializer");
			props.put("auto.offset.reset", "earliest");
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
					if (!tableToSchemaMap.containsKey(schema.hashCode())) {
						tableToSchemaMap.put(schema.hashCode(), schema);
					}
					System.out.println("Schema Map size is " + tableToSchemaMap.size());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new SchemaConsumer());
		t1.start();
		
		Thread t2 = new Thread(new MsgConsumer());
		t2.start();
	}
}
