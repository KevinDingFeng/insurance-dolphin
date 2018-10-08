/**
 * IZrxCommonService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.cpic.wss.propertyinsurance.commonservice.freight;

public interface IZrxCommonService extends java.rmi.Remote {
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse approval(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse queryPolicyStatus(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseResponse endorse(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.EndorseRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse doIssuedPaymentPolicy(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse doQueryBasicData(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse postApplication(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse queryApplicationStatus(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse cancelApplication(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse doClaimSettl(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse claimReportInfo(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
    public cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse receivePaySysInfo(cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest request) throws java.rmi.RemoteException, cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.WssException;
}
