package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayflowPro.PaymentGatewayPayflowProMapper;

public class PaymentGatewayPayflowPro implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String certsPath;
private String hostAddress;
private Long hostPort;
private Long timeout;
private String proxyAddress;
private Long proxyPort;
private String proxyLogon;
private String proxyPassword;
private String vendor;
private String userId;
private String pwd;
private String partner;
private Boolean checkAvs;
private Boolean checkCvv2;
private Boolean preAuth;
private String enableTransmit;
private String logFileName;
private Long loggingLevel;
private Long maxLogFileSize;
private Boolean stackTraceOn;
private String redirectUrl;
private String returnUrl;
private String cancelReturnUrl;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getCertsPath() {
return certsPath;
}

public void setCertsPath(String  certsPath) {
this.certsPath = certsPath;
}

public String getHostAddress() {
return hostAddress;
}

public void setHostAddress(String  hostAddress) {
this.hostAddress = hostAddress;
}

public Long getHostPort() {
return hostPort;
}

public void setHostPort(Long  hostPort) {
this.hostPort = hostPort;
}

public Long getTimeout() {
return timeout;
}

public void setTimeout(Long  timeout) {
this.timeout = timeout;
}

public String getProxyAddress() {
return proxyAddress;
}

public void setProxyAddress(String  proxyAddress) {
this.proxyAddress = proxyAddress;
}

public Long getProxyPort() {
return proxyPort;
}

public void setProxyPort(Long  proxyPort) {
this.proxyPort = proxyPort;
}

public String getProxyLogon() {
return proxyLogon;
}

public void setProxyLogon(String  proxyLogon) {
this.proxyLogon = proxyLogon;
}

public String getProxyPassword() {
return proxyPassword;
}

public void setProxyPassword(String  proxyPassword) {
this.proxyPassword = proxyPassword;
}

public String getVendor() {
return vendor;
}

public void setVendor(String  vendor) {
this.vendor = vendor;
}

public String getUserId() {
return userId;
}

public void setUserId(String  userId) {
this.userId = userId;
}

public String getPwd() {
return pwd;
}

public void setPwd(String  pwd) {
this.pwd = pwd;
}

public String getPartner() {
return partner;
}

public void setPartner(String  partner) {
this.partner = partner;
}

public Boolean getCheckAvs() {
return checkAvs;
}

public void setCheckAvs(Boolean  checkAvs) {
this.checkAvs = checkAvs;
}

public Boolean getCheckCvv2() {
return checkCvv2;
}

public void setCheckCvv2(Boolean  checkCvv2) {
this.checkCvv2 = checkCvv2;
}

public Boolean getPreAuth() {
return preAuth;
}

public void setPreAuth(Boolean  preAuth) {
this.preAuth = preAuth;
}

public String getEnableTransmit() {
return enableTransmit;
}

public void setEnableTransmit(String  enableTransmit) {
this.enableTransmit = enableTransmit;
}

public String getLogFileName() {
return logFileName;
}

public void setLogFileName(String  logFileName) {
this.logFileName = logFileName;
}

public Long getLoggingLevel() {
return loggingLevel;
}

public void setLoggingLevel(Long  loggingLevel) {
this.loggingLevel = loggingLevel;
}

public Long getMaxLogFileSize() {
return maxLogFileSize;
}

public void setMaxLogFileSize(Long  maxLogFileSize) {
this.maxLogFileSize = maxLogFileSize;
}

public Boolean getStackTraceOn() {
return stackTraceOn;
}

public void setStackTraceOn(Boolean  stackTraceOn) {
this.stackTraceOn = stackTraceOn;
}

public String getRedirectUrl() {
return redirectUrl;
}

public void setRedirectUrl(String  redirectUrl) {
this.redirectUrl = redirectUrl;
}

public String getReturnUrl() {
return returnUrl;
}

public void setReturnUrl(String  returnUrl) {
this.returnUrl = returnUrl;
}

public String getCancelReturnUrl() {
return cancelReturnUrl;
}

public void setCancelReturnUrl(String  cancelReturnUrl) {
this.cancelReturnUrl = cancelReturnUrl;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayPayflowProMapper.map(this);
}
}
