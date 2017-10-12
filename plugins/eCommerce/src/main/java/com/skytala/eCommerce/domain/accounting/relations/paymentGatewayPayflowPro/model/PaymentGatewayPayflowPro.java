package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.mapper.PaymentGatewayPayflowProMapper;

public class PaymentGatewayPayflowPro implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private Long certsPath;
private Long hostAddress;
private Long hostPort;
private Long timeout;
private Long proxyAddress;
private Long proxyPort;
private Long proxyLogon;
private Long proxyPassword;
private String vendor;
private String userId;
private Long pwd;
private String partner;
private Boolean checkAvs;
private Boolean checkCvv2;
private Boolean preAuth;
private Long enableTransmit;
private Long logFileName;
private Long loggingLevel;
private Long maxLogFileSize;
private Boolean stackTraceOn;
private Long redirectUrl;
private Long returnUrl;
private Long cancelReturnUrl;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public Long getCertsPath() {
return certsPath;
}

public void setCertsPath(Long  certsPath) {
this.certsPath = certsPath;
}

public Long getHostAddress() {
return hostAddress;
}

public void setHostAddress(Long  hostAddress) {
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

public Long getProxyAddress() {
return proxyAddress;
}

public void setProxyAddress(Long  proxyAddress) {
this.proxyAddress = proxyAddress;
}

public Long getProxyPort() {
return proxyPort;
}

public void setProxyPort(Long  proxyPort) {
this.proxyPort = proxyPort;
}

public Long getProxyLogon() {
return proxyLogon;
}

public void setProxyLogon(Long  proxyLogon) {
this.proxyLogon = proxyLogon;
}

public Long getProxyPassword() {
return proxyPassword;
}

public void setProxyPassword(Long  proxyPassword) {
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

public Long getPwd() {
return pwd;
}

public void setPwd(Long  pwd) {
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

public Long getEnableTransmit() {
return enableTransmit;
}

public void setEnableTransmit(Long  enableTransmit) {
this.enableTransmit = enableTransmit;
}

public Long getLogFileName() {
return logFileName;
}

public void setLogFileName(Long  logFileName) {
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

public Long getRedirectUrl() {
return redirectUrl;
}

public void setRedirectUrl(Long  redirectUrl) {
this.redirectUrl = redirectUrl;
}

public Long getReturnUrl() {
return returnUrl;
}

public void setReturnUrl(Long  returnUrl) {
this.returnUrl = returnUrl;
}

public Long getCancelReturnUrl() {
return cancelReturnUrl;
}

public void setCancelReturnUrl(Long  cancelReturnUrl) {
this.cancelReturnUrl = cancelReturnUrl;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayPayflowProMapper.map(this);
}
}
