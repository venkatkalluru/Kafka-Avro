package Rule;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;


public class RuleDeserializer {
    public RuleDeserializer() {

    }

    public ArrayList<RuleMessage> DeserializeRule(byte[] event) throws Exception {
        DatumReader<RuleMessage> reader = new SpecificDatumReader<>(RuleMessage.getClassSchema());
        Decoder decoder = null;
        ArrayList<RuleMessage> rmList = new ArrayList<>();
        try{
            decoder = DecoderFactory.get().binaryDecoder(event, null);
           
            RuleMessage msg = reader.read(null, decoder);
            int count = 0;
            while (msg != null) {//TODO: Find a better way to do this.
//              System.out.printf("Rule Message value = %s \n", msg);
              rmList.add(msg);
              msg = reader.read(null, decoder);
            }
			
        } catch(EOFException exception){
        //    exception.printStackTrace();
        } catch(IOException exception){
            exception.printStackTrace();
        }
        return rmList;
    }
}

