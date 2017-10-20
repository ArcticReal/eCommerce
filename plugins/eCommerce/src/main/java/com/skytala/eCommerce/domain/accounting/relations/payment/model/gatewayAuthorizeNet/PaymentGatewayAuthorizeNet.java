package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetMapper;

public class PaymentGatewayAuthorizeNet implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private Long transactionUrl;
private Long certificateAlias;
private String apiVersion;
private String delimitedData;
private String delimiterChar;
private String cpVersion;
private String cpMarketType;
private String cpDeviceType;
private String method;
private String emailCustomer;
private String emailMerchant;
private String testMode;
private String relayResponse;
private Long tranKey;
private Long userId;
private Long pwd;
private Long transDescription;
private Long duplicateWindow;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public Long getTransactionUrl() {
return transactionUrl;
}

public void setTransactionUrl(Long  transactionUrl) {
this.transactionUrl = transactionUrl;
}

public Long getCertificateAlias() {
return certificateAlias;
}

public void setCertificateAlias(Long  certificateAlias) {
this.certificateAlias = certificateAlias;
}

public String getApiVersion() {
return apiVersion;
}

public void setApiVersion(String  apiVersion) {
this.apiVersion = apiVersion;
}

public String getDelimitedData() {
return delimitedData;
}

public void setDelimitedData(String  delimitedData) {
this.delimitedData = delimitedData;
}

public String getDelimiterChar() {
return delimiterChar;
}

public void setDelimiterChar(String  delimiterChar) {
this.delimiterChar = delimiterChar;
}

public String getCpVersion() {
return cpVersion;
}

public void setCpVersion(String  cpVersion) {
this.cpVersion = cpVersion;
}

public String getCpMarketType() {
return cpMarketType;
}

public void setCpMarketType(String  cpMarketType) {
this.cpMarketType = cpMarketType;
}

public String getCpDeviceType() {
return cpDeviceType;
}

public void setCpDeviceType(String  cpDeviceType) {
this.cpDeviceType = cpDeviceType;
}

public String getMethod() {
return method;
}

public void setMethod(String  method) {
this.method = method;
}

public String getEmailCustomer() {
return emailCustomer;
}

public void setEmailCustomer(String  emailCustomer) {
this.emailCustomer = emailCustomer;
}

public String getEmailMerchant() {
return emailMerchant;
}

public void setEmailMerchant(String  emailMerchant) {
this.emailMerchant = emailMerchant;
}

public String getTestMode() {
return testMode;
}

public void setTestMode(String  testMode) {
this.testMode = testMode;
}

public String getRelayResponse() {
return relayResponse;
}

public void setRelayResponse(String  relayResponse) {
this.relayResponse = relayResponse;
}

public Long getTranKey() {
return tranKey;
}

public void setTranKey(Long  tranKey) {
this.tranKey = tranKey;
}

public Long getUserId() {
return userId;
}

public void setUserId(Long  userId) {
this.userId = userId;
}

public Long getPwd() {
return pwd;
}

public void setPwd(Long  pwd) {
this.pwd = pwd;
}

public Long getTransDescription() {
return transDescription;
}

public void setTransDescription(Long  transDescription) {
this.transDescription = transDescription;
}

public Long getDuplicateWindow() {
return duplicateWindow;
}

public void setDuplicateWindow(Long  duplicateWindow) {
this.duplicateWindow = duplicateWindow;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayAuthorizeNetMapper.map(this);
}
}
