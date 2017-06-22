/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package Rule;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class RuleMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8413801854223400947L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RuleMessage\",\"namespace\":\"Rule\",\"fields\":[{\"name\":\"table_name\",\"type\":\"string\"},{\"name\":\"schema_hash\",\"type\":\"int\"},{\"name\":\"payload\",\"type\":\"bytes\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence table_name;
  @Deprecated public int schema_hash;
  @Deprecated public java.nio.ByteBuffer payload;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public RuleMessage() {}

  /**
   * All-args constructor.
   */
  public RuleMessage(java.lang.CharSequence table_name, java.lang.Integer schema_hash, java.nio.ByteBuffer payload) {
    this.table_name = table_name;
    this.schema_hash = schema_hash;
    this.payload = payload;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return table_name;
    case 1: return schema_hash;
    case 2: return payload;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: table_name = (java.lang.CharSequence)value$; break;
    case 1: schema_hash = (java.lang.Integer)value$; break;
    case 2: payload = (java.nio.ByteBuffer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'table_name' field.
   */
  public java.lang.CharSequence getTableName() {
    return table_name;
  }

  /**
   * Sets the value of the 'table_name' field.
   * @param value the value to set.
   */
  public void setTableName(java.lang.CharSequence value) {
    this.table_name = value;
  }

  /**
   * Gets the value of the 'schema_hash' field.
   */
  public java.lang.Integer getSchemaHash() {
    return schema_hash;
  }

  /**
   * Sets the value of the 'schema_hash' field.
   * @param value the value to set.
   */
  public void setSchemaHash(java.lang.Integer value) {
    this.schema_hash = value;
  }

  /**
   * Gets the value of the 'payload' field.
   */
  public java.nio.ByteBuffer getPayload() {
    return payload;
  }

  /**
   * Sets the value of the 'payload' field.
   * @param value the value to set.
   */
  public void setPayload(java.nio.ByteBuffer value) {
    this.payload = value;
  }

  /**
   * Creates a new RuleMessage RecordBuilder.
   * @return A new RuleMessage RecordBuilder
   */
  public static Rule.RuleMessage.Builder newBuilder() {
    return new Rule.RuleMessage.Builder();
  }
  
  /**
   * Creates a new RuleMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new RuleMessage RecordBuilder
   */
  public static Rule.RuleMessage.Builder newBuilder(Rule.RuleMessage.Builder other) {
    return new Rule.RuleMessage.Builder(other);
  }
  
  /**
   * Creates a new RuleMessage RecordBuilder by copying an existing RuleMessage instance.
   * @param other The existing instance to copy.
   * @return A new RuleMessage RecordBuilder
   */
  public static Rule.RuleMessage.Builder newBuilder(Rule.RuleMessage other) {
    return new Rule.RuleMessage.Builder(other);
  }
  
  /**
   * RecordBuilder for RuleMessage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RuleMessage>
    implements org.apache.avro.data.RecordBuilder<RuleMessage> {

    private java.lang.CharSequence table_name;
    private int schema_hash;
    private java.nio.ByteBuffer payload;

    /** Creates a new Builder */
    private Builder() {
      super(Rule.RuleMessage.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Rule.RuleMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.table_name)) {
        this.table_name = data().deepCopy(fields()[0].schema(), other.table_name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.schema_hash)) {
        this.schema_hash = data().deepCopy(fields()[1].schema(), other.schema_hash);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.payload)) {
        this.payload = data().deepCopy(fields()[2].schema(), other.payload);
        fieldSetFlags()[2] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing RuleMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(Rule.RuleMessage other) {
            super(Rule.RuleMessage.SCHEMA$);
      if (isValidValue(fields()[0], other.table_name)) {
        this.table_name = data().deepCopy(fields()[0].schema(), other.table_name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.schema_hash)) {
        this.schema_hash = data().deepCopy(fields()[1].schema(), other.schema_hash);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.payload)) {
        this.payload = data().deepCopy(fields()[2].schema(), other.payload);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'table_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getTableName() {
      return table_name;
    }

    /**
      * Sets the value of the 'table_name' field.
      * @param value The value of 'table_name'.
      * @return This builder.
      */
    public Rule.RuleMessage.Builder setTableName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.table_name = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'table_name' field has been set.
      * @return True if the 'table_name' field has been set, false otherwise.
      */
    public boolean hasTableName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'table_name' field.
      * @return This builder.
      */
    public Rule.RuleMessage.Builder clearTableName() {
      table_name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'schema_hash' field.
      * @return The value.
      */
    public java.lang.Integer getSchemaHash() {
      return schema_hash;
    }

    /**
      * Sets the value of the 'schema_hash' field.
      * @param value The value of 'schema_hash'.
      * @return This builder.
      */
    public Rule.RuleMessage.Builder setSchemaHash(int value) {
      validate(fields()[1], value);
      this.schema_hash = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'schema_hash' field has been set.
      * @return True if the 'schema_hash' field has been set, false otherwise.
      */
    public boolean hasSchemaHash() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'schema_hash' field.
      * @return This builder.
      */
    public Rule.RuleMessage.Builder clearSchemaHash() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'payload' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getPayload() {
      return payload;
    }

    /**
      * Sets the value of the 'payload' field.
      * @param value The value of 'payload'.
      * @return This builder.
      */
    public Rule.RuleMessage.Builder setPayload(java.nio.ByteBuffer value) {
      validate(fields()[2], value);
      this.payload = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'payload' field has been set.
      * @return True if the 'payload' field has been set, false otherwise.
      */
    public boolean hasPayload() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'payload' field.
      * @return This builder.
      */
    public Rule.RuleMessage.Builder clearPayload() {
      payload = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public RuleMessage build() {
      try {
        RuleMessage record = new RuleMessage();
        record.table_name = fieldSetFlags()[0] ? this.table_name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.schema_hash = fieldSetFlags()[1] ? this.schema_hash : (java.lang.Integer) defaultValue(fields()[1]);
        record.payload = fieldSetFlags()[2] ? this.payload : (java.nio.ByteBuffer) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);  

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);  

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}
