package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayOrbital;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayOrbital.PaymentGatewayOrbitalMapper;

public class PaymentGatewayOrbital implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String username;
private String connectionPassword;
private String merchantId;
private String engineClass;
private String hostName;
private Long port;
private String hostNameFailover;
private Long portFailover;
private Long connectionTimeoutSeconds;
private Long readTimeoutSeconds;
private String authorizationURI;
private String sdkVersion;
private String sslSocketFactory;
private String responseType;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getUsername() {
return username;
}

public void setUsername(String  username) {
this.username = username;
}

public String getConnectionPassword() {
return connectionPassword;
}

public void setConnectionPassword(String  connectionPassword) {
this.connectionPassword = connectionPassword;
}

public String getMerchantId() {
return merchantId;
}

public void setMerchantId(String  merchantId) {
this.merchantId = merchantId;
}

public String getEngineClass() {
return engineClass;
}

public void setEngineClass(String  engineClass) {
this.engineClass = engineClass;
}

public String getHostName() {
return hostName;
}

public void setHostName(String  hostName) {
this.hostName = hostName;
}

public Long getPort() {
return port;
}

public void setPort(Long  port) {
this.port = port;
}

public String getHostNameFailover() {
return hostNameFailover;
}

public void setHostNameFailover(String  hostNameFailover) {
this.hostNameFailover = hostNameFailover;
}

public Long getPortFailover() {
return portFailover;
}

public void setPortFailover(Long  portFailover) {
this.portFailover = portFailover;
}

public Long getConnectionTimeoutSeconds() {
return connectionTimeoutSeconds;
}

public void setConnectionTimeoutSeconds(Long  connectionTimeoutSeconds) {
this.connectionTimeoutSeconds = connectionTimeoutSeconds;
}

public Long getReadTimeoutSeconds() {
return readTimeoutSeconds;
}

public void setReadTimeoutSeconds(Long  readTimeoutSeconds) {
this.readTimeoutSeconds = readTimeoutSeconds;
}

public String getAuthorizationURI() {
return authorizationURI;
}

public void setAuthorizationURI(String  authorizationURI) {
this.authorizationURI = authorizationURI;
}

public String getSdkVersion() {
return sdkVersion;
}

public void setSdkVersion(String  sdkVersion) {
this.sdkVersion = sdkVersion;
}

public String getSslSocketFactory() {
return sslSocketFactory;
}

public void setSslSocketFactory(String  sslSocketFactory) {
this.sslSocketFactory = sslSocketFactory;
}

public String getResponseType() {
return responseType;
}

public void setResponseType(String  responseType) {
this.responseType = responseType;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayOrbitalMapper.map(this);
}
}
