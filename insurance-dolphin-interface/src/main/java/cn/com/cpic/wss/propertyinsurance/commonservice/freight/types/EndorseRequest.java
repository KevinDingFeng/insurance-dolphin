/**
 * EndorseRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.cpic.wss.propertyinsurance.commonservice.freight.types;

public class EndorseRequest  implements java.io.Serializable {
    private java.lang.String checkCode;

    private cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseType endorseType;

    private java.lang.String endorsementInfo;

    private cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct productInfo;

    private cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser userInfo;

    public EndorseRequest() {
    }

    public EndorseRequest(
           java.lang.String checkCode,
           cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseType endorseType,
           java.lang.String endorsementInfo,
           cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct productInfo,
           cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser userInfo) {
           this.checkCode = checkCode;
           this.endorseType = endorseType;
           this.endorsementInfo = endorsementInfo;
           this.productInfo = productInfo;
           this.userInfo = userInfo;
    }


    /**
     * Gets the checkCode value for this EndorseRequest.
     * 
     * @return checkCode
     */
    public java.lang.String getCheckCode() {
        return checkCode;
    }


    /**
     * Sets the checkCode value for this EndorseRequest.
     * 
     * @param checkCode
     */
    public void setCheckCode(java.lang.String checkCode) {
        this.checkCode = checkCode;
    }


    /**
     * Gets the endorseType value for this EndorseRequest.
     * 
     * @return endorseType
     */
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseType getEndorseType() {
        return endorseType;
    }


    /**
     * Sets the endorseType value for this EndorseRequest.
     * 
     * @param endorseType
     */
    public void setEndorseType(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseType endorseType) {
        this.endorseType = endorseType;
    }


    /**
     * Gets the endorsementInfo value for this EndorseRequest.
     * 
     * @return endorsementInfo
     */
    public java.lang.String getEndorsementInfo() {
        return endorsementInfo;
    }


    /**
     * Sets the endorsementInfo value for this EndorseRequest.
     * 
     * @param endorsementInfo
     */
    public void setEndorsementInfo(java.lang.String endorsementInfo) {
        this.endorsementInfo = endorsementInfo;
    }


    /**
     * Gets the productInfo value for this EndorseRequest.
     * 
     * @return productInfo
     */
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct getProductInfo() {
        return productInfo;
    }


    /**
     * Sets the productInfo value for this EndorseRequest.
     * 
     * @param productInfo
     */
    public void setProductInfo(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct productInfo) {
        this.productInfo = productInfo;
    }


    /**
     * Gets the userInfo value for this EndorseRequest.
     * 
     * @return userInfo
     */
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser getUserInfo() {
        return userInfo;
    }


    /**
     * Sets the userInfo value for this EndorseRequest.
     * 
     * @param userInfo
     */
    public void setUserInfo(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser userInfo) {
        this.userInfo = userInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndorseRequest)) return false;
        EndorseRequest other = (EndorseRequest) obj;
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
            ((this.endorseType==null && other.getEndorseType()==null) || 
             (this.endorseType!=null &&
              this.endorseType.equals(other.getEndorseType()))) &&
            ((this.endorsementInfo==null && other.getEndorsementInfo()==null) || 
             (this.endorsementInfo!=null &&
              this.endorsementInfo.equals(other.getEndorsementInfo()))) &&
            ((this.productInfo==null && other.getProductInfo()==null) || 
             (this.productInfo!=null &&
              this.productInfo.equals(other.getProductInfo()))) &&
            ((this.userInfo==null && other.getUserInfo()==null) || 
             (this.userInfo!=null &&
              this.userInfo.equals(other.getUserInfo())));
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
        if (getEndorseType() != null) {
            _hashCode += getEndorseType().hashCode();
        }
        if (getEndorsementInfo() != null) {
            _hashCode += getEndorsementInfo().hashCode();
        }
        if (getProductInfo() != null) {
            _hashCode += getProductInfo().hashCode();
        }
        if (getUserInfo() != null) {
            _hashCode += getUserInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EndorseRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "checkCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endorseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "endorseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endorsementInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "endorsementInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "productInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalProduct"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "userInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "LoginUser"));
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
