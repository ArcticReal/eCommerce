package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.mapper.PaymentGatewayOrbitalMapper;

public class PaymentGatewayOrbital implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String username;
private Long connectionPassword;
private Long merchantId;
private Long engineClass;
private Long hostName;
private Long port;
private Long hostNameFailover;
private Long portFailover;
private Long connectionTimeoutSeconds;
private Long readTimeoutSeconds;
private Long authorizationURI;
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

public Long getConnectionPassword() {
return connectionPassword;
}

public void setConnectionPassword(Long  connectionPassword) {
this.connectionPassword = connectionPassword;
}

public Long getMerchantId() {
return merchantId;
}

public void setMerchantId(Long  merchantId) {
this.merchantId = merchantId;
}

public Long getEngineClass() {
return engineClass;
}

public void setEngineClass(Long  engineClass) {
this.engineClass = engineClass;
}

public Long getHostName() {
return hostName;
}

public void setHostName(Long  hostName) {
this.hostName = hostName;
}

public Long getPort() {
return port;
}

public void setPort(Long  port) {
this.port = port;
}

public Long getHostNameFailover() {
return hostNameFailover;
}

public void setHostNameFailover(Long  hostNameFailover) {
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

public Long getAuthorizationURI() {
return authorizationURI;
}

public void setAuthorizationURI(Long  authorizationURI) {
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
