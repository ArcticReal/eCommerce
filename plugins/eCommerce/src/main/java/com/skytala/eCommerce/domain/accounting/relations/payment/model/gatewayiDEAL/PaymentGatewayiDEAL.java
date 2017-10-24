package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayiDEAL.PaymentGatewayiDEALMapper;

public class PaymentGatewayiDEAL implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String merchantId;
private String merchantSubId;
private String merchantReturnURL;
private String acquirerURL;
private String acquirerTimeout;
private String privateCert;
private String acquirerKeyStoreFilename;
private String acquirerKeyStorePassword;
private String merchantKeyStoreFilename;
private String merchantKeyStorePassword;
private String expirationPeriod;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getMerchantId() {
return merchantId;
}

public void setMerchantId(String  merchantId) {
this.merchantId = merchantId;
}

public String getMerchantSubId() {
return merchantSubId;
}

public void setMerchantSubId(String  merchantSubId) {
this.merchantSubId = merchantSubId;
}

public String getMerchantReturnURL() {
return merchantReturnURL;
}

public void setMerchantReturnURL(String  merchantReturnURL) {
this.merchantReturnURL = merchantReturnURL;
}

public String getAcquirerURL() {
return acquirerURL;
}

public void setAcquirerURL(String  acquirerURL) {
this.acquirerURL = acquirerURL;
}

public String getAcquirerTimeout() {
return acquirerTimeout;
}

public void setAcquirerTimeout(String  acquirerTimeout) {
this.acquirerTimeout = acquirerTimeout;
}

public String getPrivateCert() {
return privateCert;
}

public void setPrivateCert(String  privateCert) {
this.privateCert = privateCert;
}

public String getAcquirerKeyStoreFilename() {
return acquirerKeyStoreFilename;
}

public void setAcquirerKeyStoreFilename(String  acquirerKeyStoreFilename) {
this.acquirerKeyStoreFilename = acquirerKeyStoreFilename;
}

public String getAcquirerKeyStorePassword() {
return acquirerKeyStorePassword;
}

public void setAcquirerKeyStorePassword(String  acquirerKeyStorePassword) {
this.acquirerKeyStorePassword = acquirerKeyStorePassword;
}

public String getMerchantKeyStoreFilename() {
return merchantKeyStoreFilename;
}

public void setMerchantKeyStoreFilename(String  merchantKeyStoreFilename) {
this.merchantKeyStoreFilename = merchantKeyStoreFilename;
}

public String getMerchantKeyStorePassword() {
return merchantKeyStorePassword;
}

public void setMerchantKeyStorePassword(String  merchantKeyStorePassword) {
this.merchantKeyStorePassword = merchantKeyStorePassword;
}

public String getExpirationPeriod() {
return expirationPeriod;
}

public void setExpirationPeriod(String  expirationPeriod) {
this.expirationPeriod = expirationPeriod;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayiDEALMapper.map(this);
}
}
