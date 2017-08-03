package Kafka;

import Message.EventMessage;
import Message.EventMessageSerializer;
import oracle.goldengate.generic_wrapper;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SimpleProducer {
    private final Producer<String, byte[]> kafkaProducer;
    private final Producer<String, String> schemaProducer;
    private static final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    public SimpleProducer() {
        logger.debug("added props");
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.204.99.230:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("compression.type", "gzip");
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        kafkaProducer = new KafkaProducer(props);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        schemaProducer = new KafkaProducer(props);
    }

    public void publish(byte[] event, String Id, String topicName) throws ExecutionException, InterruptedException {
        logger.debug("Send message");
        RecordMetadata m = kafkaProducer.send(new ProducerRecord<String, byte[]>(
                topicName, Id, event)).get();
        System.out.println("Message produced, offset: " + m.offset());
        System.out.println("Message produced, partition : " + m.partition());
        System.out.println("Message produced, topic: " + m.topic());
    }
    
    public void publish(String schema, String Id, String topicName) throws ExecutionException, InterruptedException {
        logger.debug("Send message");
        RecordMetadata m = schemaProducer.send(new ProducerRecord<String, String>(
                topicName, Id, schema)).get();
        System.out.println("Message produced, offset: " + m.offset());
        System.out.println("Message produced, partition : " + m.partition());
        System.out.println("Message produced, topic: " + m.topic());
    }

    public static void main(String[] args) {
        SimpleProducer sp = new SimpleProducer();
        
        
        EventMessage event = new EventMessage();
        String[] machines = {"pump_1", "pump_2", "tank_1", "tank_2"};
        event.setBuilding("building_3");
        event.setId("5ba51e3");
        event.setDate(new Date().getTime());
        
        EventMessage event2 = new EventMessage();
        String[] machines2 = {"pump_1", "pump_2", "tank_1", "tank_2"};
        event2.setBuilding("building_3");
        event2.setId("5ba51e3");
        event2.setDate(new Date().getTime());
        
        EventMessage event3 = new EventMessage();
        String[] machines3 = {"pump_1", "pump_2", "tank_1", "tank_2"};
        event3.setBuilding("building_3");
        event3.setId("5ba51e3");
        event3.setDate(new Date().getTime());
        
        float minX = 1f;
        float maxX = 100.0f;
        Random rand = new Random();
        try {
        	
        	sp.publish(event.getSchema().toString(), "event-schema", "schema-topic-1");
        	sp.publish(event2.getSchema().toString(), "event2-schema", "schema-topic-1");
        	
            EventMessageSerializer eventMessageSerializer = new EventMessageSerializer();
            
            for (int i = 0; i < 2; i++) {
                event.setStatus(rand.nextFloat() * (maxX - minX) + minX);
                event.setMachine(machines[new Random().nextInt(machines.length)]);
                
                generic_wrapper gw = new generic_wrapper();
                gw.setTableName("Event");
                gw.setSchemaHash(event.hashCode());
                gw.setPayload(ByteBuffer.wrap(eventMessageSerializer.serializeMessage(event)));
                
                sp.publish(eventMessageSerializer.serializeGenericMessage(gw), event.getId().toString(), "msg-topic-1");
                
               ///////////// event 2 /////////// 
                
                event2.setStatus(rand.nextFloat() * (maxX - minX) + minX);
                event2.setMachine(machines2[new Random().nextInt(machines2.length)]);
                
                generic_wrapper gw2 = new generic_wrapper();
                gw2.setTableName("Event2");
                gw2.setSchemaHash(event2.hashCode());
                gw2.setPayload(ByteBuffer.wrap(eventMessageSerializer.serializeMessage(event2)));
                
                sp.publish(eventMessageSerializer.serializeGenericMessage(gw2), event2.getId().toString(), "msg-topic-1");
            }
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
