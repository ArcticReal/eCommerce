package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.mapper.PaymentGatewayiDEALMapper;

public class PaymentGatewayiDEAL implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private Long merchantId;
private Long merchantSubId;
private Long merchantReturnURL;
private Long acquirerURL;
private Long acquirerTimeout;
private Long privateCert;
private Long acquirerKeyStoreFilename;
private Long acquirerKeyStorePassword;
private Long merchantKeyStoreFilename;
private Long merchantKeyStorePassword;
private Long expirationPeriod;

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

public Long getMerchantSubId() {
return merchantSubId;
}

public void setMerchantSubId(Long  merchantSubId) {
this.merchantSubId = merchantSubId;
}

public Long getMerchantReturnURL() {
return merchantReturnURL;
}

public void setMerchantReturnURL(Long  merchantReturnURL) {
this.merchantReturnURL = merchantReturnURL;
}

public Long getAcquirerURL() {
return acquirerURL;
}

public void setAcquirerURL(Long  acquirerURL) {
this.acquirerURL = acquirerURL;
}

public Long getAcquirerTimeout() {
return acquirerTimeout;
}

public void setAcquirerTimeout(Long  acquirerTimeout) {
this.acquirerTimeout = acquirerTimeout;
}

public Long getPrivateCert() {
return privateCert;
}

public void setPrivateCert(Long  privateCert) {
this.privateCert = privateCert;
}

public Long getAcquirerKeyStoreFilename() {
return acquirerKeyStoreFilename;
}

public void setAcquirerKeyStoreFilename(Long  acquirerKeyStoreFilename) {
this.acquirerKeyStoreFilename = acquirerKeyStoreFilename;
}

public Long getAcquirerKeyStorePassword() {
return acquirerKeyStorePassword;
}

public void setAcquirerKeyStorePassword(Long  acquirerKeyStorePassword) {
this.acquirerKeyStorePassword = acquirerKeyStorePassword;
}

public Long getMerchantKeyStoreFilename() {
return merchantKeyStoreFilename;
}

public void setMerchantKeyStoreFilename(Long  merchantKeyStoreFilename) {
this.merchantKeyStoreFilename = merchantKeyStoreFilename;
}

public Long getMerchantKeyStorePassword() {
return merchantKeyStorePassword;
}

public void setMerchantKeyStorePassword(Long  merchantKeyStorePassword) {
this.merchantKeyStorePassword = merchantKeyStorePassword;
}

public Long getExpirationPeriod() {
return expirationPeriod;
}

public void setExpirationPeriod(Long  expirationPeriod) {
this.expirationPeriod = expirationPeriod;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayiDEALMapper.map(this);
}
}
