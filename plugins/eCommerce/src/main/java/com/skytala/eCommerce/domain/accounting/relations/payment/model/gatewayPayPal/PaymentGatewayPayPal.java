package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayPal;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayPal.PaymentGatewayPayPalMapper;

public class PaymentGatewayPayPal implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String businessEmail;
private String apiUserName;
private String apiPassword;
private String apiSignature;
private String apiEnvironment;
private String notifyUrl;
private String returnUrl;
private String cancelReturnUrl;
private String imageUrl;
private String confirmTemplate;
private String redirectUrl;
private String confirmUrl;
private String shippingCallbackUrl;
private Boolean requireConfirmedShipping;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getBusinessEmail() {
return businessEmail;
}

public void setBusinessEmail(String  businessEmail) {
this.businessEmail = businessEmail;
}

public String getApiUserName() {
return apiUserName;
}

public void setApiUserName(String  apiUserName) {
this.apiUserName = apiUserName;
}

public String getApiPassword() {
return apiPassword;
}

public void setApiPassword(String  apiPassword) {
this.apiPassword = apiPassword;
}

public String getApiSignature() {
return apiSignature;
}

public void setApiSignature(String  apiSignature) {
this.apiSignature = apiSignature;
}

public String getApiEnvironment() {
return apiEnvironment;
}

public void setApiEnvironment(String  apiEnvironment) {
this.apiEnvironment = apiEnvironment;
}

public String getNotifyUrl() {
return notifyUrl;
}

public void setNotifyUrl(String  notifyUrl) {
this.notifyUrl = notifyUrl;
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

public String getImageUrl() {
return imageUrl;
}

public void setImageUrl(String  imageUrl) {
this.imageUrl = imageUrl;
}

public String getConfirmTemplate() {
return confirmTemplate;
}

public void setConfirmTemplate(String  confirmTemplate) {
this.confirmTemplate = confirmTemplate;
}

public String getRedirectUrl() {
return redirectUrl;
}

public void setRedirectUrl(String  redirectUrl) {
this.redirectUrl = redirectUrl;
}

public String getConfirmUrl() {
return confirmUrl;
}

public void setConfirmUrl(String  confirmUrl) {
this.confirmUrl = confirmUrl;
}

public String getShippingCallbackUrl() {
return shippingCallbackUrl;
}

public void setShippingCallbackUrl(String  shippingCallbackUrl) {
this.shippingCallbackUrl = shippingCallbackUrl;
}

public Boolean getRequireConfirmedShipping() {
return requireConfirmedShipping;
}

public void setRequireConfirmedShipping(Boolean  requireConfirmedShipping) {
this.requireConfirmedShipping = requireConfirmedShipping;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayPayPalMapper.map(this);
}
}
