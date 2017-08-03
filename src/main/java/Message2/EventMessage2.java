/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package Message2;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class EventMessage2 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2658158388926073839L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"EventMessage2\",\"namespace\":\"Message2\",\"fields\":[{\"name\":\"machine2\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"date\",\"type\":\"long\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"building\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence machine2;
  @Deprecated public java.lang.CharSequence id;
  @Deprecated public long date;
  @Deprecated public java.lang.CharSequence status;
  @Deprecated public java.lang.CharSequence building;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public EventMessage2() {}

  /**
   * All-args constructor.
   */
  public EventMessage2(java.lang.CharSequence machine2, java.lang.CharSequence id, java.lang.Long date, java.lang.CharSequence status, java.lang.CharSequence building) {
    this.machine2 = machine2;
    this.id = id;
    this.date = date;
    this.status = status;
    this.building = building;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return machine2;
    case 1: return id;
    case 2: return date;
    case 3: return status;
    case 4: return building;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: machine2 = (java.lang.CharSequence)value$; break;
    case 1: id = (java.lang.CharSequence)value$; break;
    case 2: date = (java.lang.Long)value$; break;
    case 3: status = (java.lang.CharSequence)value$; break;
    case 4: building = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'machine2' field.
   */
  public java.lang.CharSequence getMachine2() {
    return machine2;
  }

  /**
   * Sets the value of the 'machine2' field.
   * @param value the value to set.
   */
  public void setMachine2(java.lang.CharSequence value) {
    this.machine2 = value;
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'date' field.
   */
  public java.lang.Long getDate() {
    return date;
  }

  /**
   * Sets the value of the 'date' field.
   * @param value the value to set.
   */
  public void setDate(java.lang.Long value) {
    this.date = value;
  }

  /**
   * Gets the value of the 'status' field.
   */
  public java.lang.CharSequence getStatus() {
    return status;
  }

  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.CharSequence value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'building' field.
   */
  public java.lang.CharSequence getBuilding() {
    return building;
  }

  /**
   * Sets the value of the 'building' field.
   * @param value the value to set.
   */
  public void setBuilding(java.lang.CharSequence value) {
    this.building = value;
  }

  /**
   * Creates a new EventMessage2 RecordBuilder.
   * @return A new EventMessage2 RecordBuilder
   */
  public static Message2.EventMessage2.Builder newBuilder() {
    return new Message2.EventMessage2.Builder();
  }
  
  /**
   * Creates a new EventMessage2 RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new EventMessage2 RecordBuilder
   */
  public static Message2.EventMessage2.Builder newBuilder(Message2.EventMessage2.Builder other) {
    return new Message2.EventMessage2.Builder(other);
  }
  
  /**
   * Creates a new EventMessage2 RecordBuilder by copying an existing EventMessage2 instance.
   * @param other The existing instance to copy.
   * @return A new EventMessage2 RecordBuilder
   */
  public static Message2.EventMessage2.Builder newBuilder(Message2.EventMessage2 other) {
    return new Message2.EventMessage2.Builder(other);
  }
  
  /**
   * RecordBuilder for EventMessage2 instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<EventMessage2>
    implements org.apache.avro.data.RecordBuilder<EventMessage2> {

    private java.lang.CharSequence machine2;
    private java.lang.CharSequence id;
    private long date;
    private java.lang.CharSequence status;
    private java.lang.CharSequence building;

    /** Creates a new Builder */
    private Builder() {
      super(Message2.EventMessage2.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Message2.EventMessage2.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.machine2)) {
        this.machine2 = data().deepCopy(fields()[0].schema(), other.machine2);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.date)) {
        this.date = data().deepCopy(fields()[2].schema(), other.date);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.status)) {
        this.status = data().deepCopy(fields()[3].schema(), other.status);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.building)) {
        this.building = data().deepCopy(fields()[4].schema(), other.building);
        fieldSetFlags()[4] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing EventMessage2 instance
     * @param other The existing instance to copy.
     */
    private Builder(Message2.EventMessage2 other) {
            super(Message2.EventMessage2.SCHEMA$);
      if (isValidValue(fields()[0], other.machine2)) {
        this.machine2 = data().deepCopy(fields()[0].schema(), other.machine2);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.date)) {
        this.date = data().deepCopy(fields()[2].schema(), other.date);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.status)) {
        this.status = data().deepCopy(fields()[3].schema(), other.status);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.building)) {
        this.building = data().deepCopy(fields()[4].schema(), other.building);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'machine2' field.
      * @return The value.
      */
    public java.lang.CharSequence getMachine2() {
      return machine2;
    }

    /**
      * Sets the value of the 'machine2' field.
      * @param value The value of 'machine2'.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder setMachine2(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.machine2 = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'machine2' field has been set.
      * @return True if the 'machine2' field has been set, false otherwise.
      */
    public boolean hasMachine2() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'machine2' field.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder clearMachine2() {
      machine2 = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder setId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.id = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder clearId() {
      id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'date' field.
      * @return The value.
      */
    public java.lang.Long getDate() {
      return date;
    }

    /**
      * Sets the value of the 'date' field.
      * @param value The value of 'date'.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder setDate(long value) {
      validate(fields()[2], value);
      this.date = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'date' field has been set.
      * @return True if the 'date' field has been set, false otherwise.
      */
    public boolean hasDate() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'date' field.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder clearDate() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.CharSequence getStatus() {
      return status;
    }

    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder setStatus(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.status = value;
      fieldSetFlags()[3] = true;
      return this; 
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder clearStatus() {
      status = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'building' field.
      * @return The value.
      */
    public java.lang.CharSequence getBuilding() {
      return building;
    }

    /**
      * Sets the value of the 'building' field.
      * @param value The value of 'building'.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder setBuilding(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.building = value;
      fieldSetFlags()[4] = true;
      return this; 
    }

    /**
      * Checks whether the 'building' field has been set.
      * @return True if the 'building' field has been set, false otherwise.
      */
    public boolean hasBuilding() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'building' field.
      * @return This builder.
      */
    public Message2.EventMessage2.Builder clearBuilding() {
      building = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    public EventMessage2 build() {
      try {
        EventMessage2 record = new EventMessage2();
        record.machine2 = fieldSetFlags()[0] ? this.machine2 : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.id = fieldSetFlags()[1] ? this.id : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.date = fieldSetFlags()[2] ? this.date : (java.lang.Long) defaultValue(fields()[2]);
        record.status = fieldSetFlags()[3] ? this.status : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.building = fieldSetFlags()[4] ? this.building : (java.lang.CharSequence) defaultValue(fields()[4]);
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