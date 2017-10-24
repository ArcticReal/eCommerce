package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySecurePay;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewaySecurePay.PaymentGatewaySecurePayMapper;

public class PaymentGatewaySecurePay implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String merchantId;
private String pwd;
private String serverURL;
private Long processTimeout;
private Boolean enableAmountRound;

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

public String getPwd() {
return pwd;
}

public void setPwd(String  pwd) {
this.pwd = pwd;
}

public String getServerURL() {
return serverURL;
}

public void setServerURL(String  serverURL) {
this.serverURL = serverURL;
}

public Long getProcessTimeout() {
return processTimeout;
}

public void setProcessTimeout(Long  processTimeout) {
this.processTimeout = processTimeout;
}

public Boolean getEnableAmountRound() {
return enableAmountRound;
}

public void setEnableAmountRound(Boolean  enableAmountRound) {
this.enableAmountRound = enableAmountRound;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewaySecurePayMapper.map(this);
}
}
