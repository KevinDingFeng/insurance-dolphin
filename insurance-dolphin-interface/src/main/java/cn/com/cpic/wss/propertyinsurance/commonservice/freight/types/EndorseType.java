/**
 * EndorseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.cpic.wss.propertyinsurance.commonservice.freight.types;

public class EndorseType  implements java.io.Serializable {
    private java.lang.String typeCode;

    public EndorseType() {
    }

    public EndorseType(
           java.lang.String typeCode) {
           this.typeCode = typeCode;
    }


    /**
     * Gets the typeCode value for this EndorseType.
     * 
     * @return typeCode
     */
    public java.lang.String getTypeCode() {
        return typeCode;
    }


    /**
     * Sets the typeCode value for this EndorseType.
     * 
     * @param typeCode
     */
    public void setTypeCode(java.lang.String typeCode) {
        this.typeCode = typeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndorseType)) return false;
        EndorseType other = (EndorseType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.typeCode==null && other.getTypeCode()==null) || 
             (this.typeCode!=null &&
              this.typeCode.equals(other.getTypeCode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTypeCode() != null) {
            _hashCode += getTypeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EndorseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "typeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
