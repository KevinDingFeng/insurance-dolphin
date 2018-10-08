/**
 * EndorseResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.cpic.wss.propertyinsurance.commonservice.freight.types;

public class EndorseResponse  implements java.io.Serializable {
    private java.lang.String checkCode;

    private java.lang.String endorsementInfo;

    private cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage sysMessage;

    public EndorseResponse() {
    }

    public EndorseResponse(
           java.lang.String checkCode,
           java.lang.String endorsementInfo,
           cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage sysMessage) {
           this.checkCode = checkCode;
           this.endorsementInfo = endorsementInfo;
           this.sysMessage = sysMessage;
    }


    /**
     * Gets the checkCode value for this EndorseResponse.
     * 
     * @return checkCode
     */
    public java.lang.String getCheckCode() {
        return checkCode;
    }


    /**
     * Sets the checkCode value for this EndorseResponse.
     * 
     * @param checkCode
     */
    public void setCheckCode(java.lang.String checkCode) {
        this.checkCode = checkCode;
    }


    /**
     * Gets the endorsementInfo value for this EndorseResponse.
     * 
     * @return endorsementInfo
     */
    public java.lang.String getEndorsementInfo() {
        return endorsementInfo;
    }


    /**
     * Sets the endorsementInfo value for this EndorseResponse.
     * 
     * @param endorsementInfo
     */
    public void setEndorsementInfo(java.lang.String endorsementInfo) {
        this.endorsementInfo = endorsementInfo;
    }


    /**
     * Gets the sysMessage value for this EndorseResponse.
     * 
     * @return sysMessage
     */
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage getSysMessage() {
        return sysMessage;
    }


    /**
     * Sets the sysMessage value for this EndorseResponse.
     * 
     * @param sysMessage
     */
    public void setSysMessage(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage sysMessage) {
        this.sysMessage = sysMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndorseResponse)) return false;
        EndorseResponse other = (EndorseResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.checkCode==null && other.getCheckCode()==null) || 
             (this.checkCode!=null &&
              this.checkCode.equals(other.getCheckCode()))) &&
            ((this.endorsementInfo==null && other.getEndorsementInfo()==null) || 
             (this.endorsementInfo!=null &&
              this.endorsementInfo.equals(other.getEndorsementInfo()))) &&
            ((this.sysMessage==null && other.getSysMessage()==null) || 
             (this.sysMessage!=null &&
              this.sysMessage.equals(other.getSysMessage())));
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
        if (getCheckCode() != null) {
            _hashCode += getCheckCode().hashCode();
        }
        if (getEndorsementInfo() != null) {
            _hashCode += getEndorsementInfo().hashCode();
        }
        if (getSysMessage() != null) {
            _hashCode += getSysMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EndorseResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "checkCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endorsementInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "endorsementInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sysMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "sysMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "SysMessage"));
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
