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
private Long businessEmail;
private String apiUserName;
private String apiPassword;
private String apiSignature;
private String apiEnvironment;
private Long notifyUrl;
private Long returnUrl;
private Long cancelReturnUrl;
private Long imageUrl;
private Long confirmTemplate;
private Long redirectUrl;
private Long confirmUrl;
private String shippingCallbackUrl;
private Boolean requireConfirmedShipping;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public Long getBusinessEmail() {
return businessEmail;
}

public void setBusinessEmail(Long  businessEmail) {
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

public Long getNotifyUrl() {
return notifyUrl;
}

public void setNotifyUrl(Long  notifyUrl) {
this.notifyUrl = notifyUrl;
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

public Long getImageUrl() {
return imageUrl;
}

public void setImageUrl(Long  imageUrl) {
this.imageUrl = imageUrl;
}

public Long getConfirmTemplate() {
return confirmTemplate;
}

public void setConfirmTemplate(Long  confirmTemplate) {
this.confirmTemplate = confirmTemplate;
}

public Long getRedirectUrl() {
return redirectUrl;
}

public void setRedirectUrl(Long  redirectUrl) {
this.redirectUrl = redirectUrl;
}

public Long getConfirmUrl() {
return confirmUrl;
}

public void setConfirmUrl(Long  confirmUrl) {
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
