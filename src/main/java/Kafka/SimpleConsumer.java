package Kafka;

import Rule.RuleDeserializer;
import Rule.RuleMessage;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkTaskContext;

import io.confluent.connect.s3.S3SinkConnectorConfig;
import io.confluent.connect.s3.TopicPartitionWriter;
import io.confluent.connect.s3.storage.S3Storage;
import io.confluent.connect.storage.format.Format;
import io.confluent.connect.storage.format.RecordWriterProvider;
import io.confluent.connect.storage.partitioner.Partitioner;
import io.confluent.connect.storage.partitioner.PartitionerConfig;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class SimpleConsumer {
	private static Map<Integer, String> tableToSchemaMap = new HashMap<>();
	private static Map<String, DataFileWriter<GenericRecord>> tableToFileWriter = new HashMap<>();
	private static Map<String, Integer> tableNameToHashCode = new HashMap<>();
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
								
								DatumReader<GenericRecord> reader = new SpecificDatumReader<>(schema);
								Decoder decoder = null;
								try{
									decoder = DecoderFactory.get().binaryDecoder(ruleMessage.getPayload().array(), null);
								   
									GenericRecord msg = reader.read(null, decoder);
									System.out.println("Decoded CDC data is " + msg);
//									writeToFile(ruleMessage.getTableName().toString(), ruleMessage.getSchemaHash(), msg);
									writeToS3(ruleMessage.getTableName().toString(), ruleMessage.getSchemaHash(), msg);
									
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

		private void writeToS3(String string, Integer schemaHash, GenericRecord msg) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
			// TODO Auto-generated method stub
			
			TopicPartition tp = new TopicPartition("test-topic", 0);
			
			Map<String, String> props = new HashMap<>();
			S3SinkConnectorConfig conf = new S3SinkConnectorConfig(props);
			String url = "test-url";
			S3Storage storage = new S3Storage(conf, url);
		
			
			@SuppressWarnings({ "unchecked", "unchecked" })
			Class<Format<S3SinkConnectorConfig, String>> formatClass =
				(Class<Format<S3SinkConnectorConfig, String>>) conf.getClass(S3SinkConnectorConfig.FORMAT_CLASS_CONFIG);
			RecordWriterProvider<S3SinkConnectorConfig> writerProvider = formatClass.getConstructor(S3Storage.class).newInstance(storage).getRecordWriterProvider();
			
			Partitioner<FieldSchema> partitioner = newPartitioner(conf);
			SinkTaskContext context = null;
			TopicPartitionWriter writer = new TopicPartitionWriter(tp, storage, writerProvider, partitioner, conf, context);
			
		}
		
	  private Partitioner<FieldSchema> newPartitioner(S3SinkConnectorConfig config)
		  throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		@SuppressWarnings("unchecked")
		Class<? extends Partitioner<FieldSchema>> partitionerClass =
			(Class<? extends Partitioner<FieldSchema>>)
				config.getClass(PartitionerConfig.PARTITIONER_CLASS_CONFIG);

		Partitioner<FieldSchema> partitioner = partitionerClass.newInstance();

		Map<String, Object> plainValues = new HashMap<>(config.plainValues());
		Map<String, ?> originals = config.originals();
		for (String originalKey : originals.keySet()) {
		  if (!plainValues.containsKey(originalKey)) {
			// pass any additional configs down to the partitioner so that custom partitioners can have their own configs
			plainValues.put(originalKey, originals.get(originalKey));
		  }
		}
		partitioner.configure(plainValues);

		return partitioner;
	  }

		private void writeToFile(String tableName, Integer hashCode, GenericRecord avroData) throws IOException {
			// TODO Auto-generated method stub
			String hashCodedTableName = tableName + hashCode.toString() + ".avro";
			String fileName;
			DataFileWriter<GenericRecord> dataFileWriter;
			if (tableToFileWriter.containsKey(hashCodedTableName)) {
				dataFileWriter = tableToFileWriter.get(hashCodedTableName);
				
			} else {
				Integer oldHasCode = tableNameToHashCode.get(tableName);
				if (oldHasCode!= null) {
					dataFileWriter = tableToFileWriter.get(tableName);
					System.out.println("Closing the old file as we are going to write to new file" + "Old HasCode is " + oldHasCode);
					dataFileWriter.close();
					tableToFileWriter.remove(tableName);
				}
				
				Schema schema = new Schema.Parser().parse(tableToSchemaMap.get(hashCode));
				DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
				dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
				tableToFileWriter.put(hashCodedTableName, dataFileWriter);
				File file = new File(hashCodedTableName);
				dataFileWriter.create(schema, file);				
			}
			dataFileWriter.append(avroData);
			dataFileWriter.flush();
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
