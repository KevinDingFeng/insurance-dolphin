package com.shenghesun.util;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 类说明
 * @author wangjunyu
 * @createDate 2016-10-20 下午8:53:16
 * @version V1.0
 */
public class ClientLoginInterceptor extends AbstractPhaseInterceptor<SoapMessage>
{

    public ClientLoginInterceptor(String username, String password) 
    {
        super(Phase.PREPARE_SEND);  
        this.username = username;
        this.password = password;
    }


    private String username;  
    private String password;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void handleMessage(SoapMessage soap) throws Fault 
    {
        List<Header> headers = soap.getHeaders();
        Document doc = DOMUtils.createDocument();
        Element auth = doc.createElement("authrity");
        Element username = doc.createElement("username");
        Element password = doc.createElement("password");
        
        username.setTextContent(this.username);
        password.setTextContent(this.password);
        
        auth.appendChild(username);
        auth.appendChild(password);
        
        headers.add(0, new Header(new QName("tiamaes"),auth));
    }

}