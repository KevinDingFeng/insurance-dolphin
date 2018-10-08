/**
 * FreightCommonServicePortSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.cpic.wss.propertyinsurance.commonservice.freight;

public class FreightCommonServicePortSoapBindingStub extends org.apache.axis.client.Stub implements cn.com.cpic.wss.propertyinsurance.commonservice.freight.IZrxCommonService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[11];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("approval");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryPolicyStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("endorse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "endorseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("doIssuedPaymentPolicy");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("doQueryBasicData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("postApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryApplicationStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cancelApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("doClaimSettl");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("claimReportInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("receivePaySysInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest"), cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse"));
        oper.setReturnClass(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approvalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault"),
                      "cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException",
                      new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException"), 
                      true
                     ));
        _operations[10] = oper;

    }

    public FreightCommonServicePortSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public FreightCommonServicePortSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public FreightCommonServicePortSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalProduct");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalRequest");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "ApprovalResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseRequest");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseResponse");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "EndorseType");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "LoginUser");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "SysMessage");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "WssException");
            cachedSerQNames.add(qName);
            cls = cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse approval(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "approval"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse queryPolicyStatus(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "queryPolicyStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse endorse(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "endorse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse doIssuedPaymentPolicy(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "doIssuedPaymentPolicy"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse doQueryBasicData(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "doQueryBasicData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse postApplication(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "postApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse queryApplicationStatus(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "queryApplicationStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse cancelApplication(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "cancelApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse doClaimSettl(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "doClaimSettl"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse claimReportInfo(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "claimReportInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse receivePaySysInfo(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types", "receivePaySysInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse) org.apache.axis.utils.JavaUtils.convert(_resp, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) {
              throw (cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
