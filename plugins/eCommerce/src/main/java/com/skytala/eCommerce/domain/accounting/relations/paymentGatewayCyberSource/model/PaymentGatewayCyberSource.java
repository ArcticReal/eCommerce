package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.mapper.PaymentGatewayCyberSourceMapper;

public class PaymentGatewayCyberSource implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private Long merchantId;
private String apiVersion;
private String production;
private Long keysDir;
private Long keysFile;
private String logEnabled;
private Long logDir;
private Long logFile;
private Long logSize;
private Long merchantDescr;
private Long merchantContact;
private String autoBill;
private Boolean enableDav;
private Boolean fraudScore;
private String ignoreAvs;
private Boolean disableBillAvs;
private Long avsDeclineCodes;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public Long getMerchantId() {
return merchantId;
}

public void setMerchantId(Long  merchantId) {
this.merchantId = merchantId;
}

public String getApiVersion() {
return apiVersion;
}

public void setApiVersion(String  apiVersion) {
this.apiVersion = apiVersion;
}

public String getProduction() {
return production;
}

public void setProduction(String  production) {
this.production = production;
}

public Long getKeysDir() {
return keysDir;
}

public void setKeysDir(Long  keysDir) {
this.keysDir = keysDir;
}

public Long getKeysFile() {
return keysFile;
}

public void setKeysFile(Long  keysFile) {
this.keysFile = keysFile;
}

public String getLogEnabled() {
return logEnabled;
}

public void setLogEnabled(String  logEnabled) {
this.logEnabled = logEnabled;
}

public Long getLogDir() {
return logDir;
}

public void setLogDir(Long  logDir) {
this.logDir = logDir;
}

public Long getLogFile() {
return logFile;
}

public void setLogFile(Long  logFile) {
this.logFile = logFile;
}

public Long getLogSize() {
return logSize;
}

public void setLogSize(Long  logSize) {
this.logSize = logSize;
}

public Long getMerchantDescr() {
return merchantDescr;
}

public void setMerchantDescr(Long  merchantDescr) {
this.merchantDescr = merchantDescr;
}

public Long getMerchantContact() {
return merchantContact;
}

public void setMerchantContact(Long  merchantContact) {
this.merchantContact = merchantContact;
}

public String getAutoBill() {
return autoBill;
}

public void setAutoBill(String  autoBill) {
this.autoBill = autoBill;
}

public Boolean getEnableDav() {
return enableDav;
}

public void setEnableDav(Boolean  enableDav) {
this.enableDav = enableDav;
}

public Boolean getFraudScore() {
return fraudScore;
}

public void setFraudScore(Boolean  fraudScore) {
this.fraudScore = fraudScore;
}

public String getIgnoreAvs() {
return ignoreAvs;
}

public void setIgnoreAvs(String  ignoreAvs) {
this.ignoreAvs = ignoreAvs;
}

public Boolean getDisableBillAvs() {
return disableBillAvs;
}

public void setDisableBillAvs(Boolean  disableBillAvs) {
this.disableBillAvs = disableBillAvs;
}

public Long getAvsDeclineCodes() {
return avsDeclineCodes;
}

public void setAvsDeclineCodes(Long  avsDeclineCodes) {
this.avsDeclineCodes = avsDeclineCodes;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayCyberSourceMapper.map(this);
}
}
