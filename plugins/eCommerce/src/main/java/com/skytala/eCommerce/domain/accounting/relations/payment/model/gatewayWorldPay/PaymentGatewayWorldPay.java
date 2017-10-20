package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayWorldPay.PaymentGatewayWorldPayMapper;

public class PaymentGatewayWorldPay implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private Long redirectUrl;
private Long instId;
private Boolean authMode;
private Boolean fixContact;
private Boolean hideContact;
private Boolean hideCurrency;
private String langId;
private Boolean noLanguageMenu;
private Boolean withDelivery;
private Long testMode;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public Long getRedirectUrl() {
return redirectUrl;
}

public void setRedirectUrl(Long  redirectUrl) {
this.redirectUrl = redirectUrl;
}

public Long getInstId() {
return instId;
}

public void setInstId(Long  instId) {
this.instId = instId;
}

public Boolean getAuthMode() {
return authMode;
}

public void setAuthMode(Boolean  authMode) {
this.authMode = authMode;
}

public Boolean getFixContact() {
return fixContact;
}

public void setFixContact(Boolean  fixContact) {
this.fixContact = fixContact;
}

public Boolean getHideContact() {
return hideContact;
}

public void setHideContact(Boolean  hideContact) {
this.hideContact = hideContact;
}

public Boolean getHideCurrency() {
return hideCurrency;
}

public void setHideCurrency(Boolean  hideCurrency) {
this.hideCurrency = hideCurrency;
}

public String getLangId() {
return langId;
}

public void setLangId(String  langId) {
this.langId = langId;
}

public Boolean getNoLanguageMenu() {
return noLanguageMenu;
}

public void setNoLanguageMenu(Boolean  noLanguageMenu) {
this.noLanguageMenu = noLanguageMenu;
}

public Boolean getWithDelivery() {
return withDelivery;
}

public void setWithDelivery(Boolean  withDelivery) {
this.withDelivery = withDelivery;
}

public Long getTestMode() {
return testMode;
}

public void setTestMode(Long  testMode) {
this.testMode = testMode;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayWorldPayMapper.map(this);
}
}
