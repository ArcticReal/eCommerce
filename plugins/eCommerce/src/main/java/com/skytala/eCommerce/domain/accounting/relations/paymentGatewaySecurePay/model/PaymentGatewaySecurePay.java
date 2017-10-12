package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.mapper.PaymentGatewaySecurePayMapper;

public class PaymentGatewaySecurePay implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private Long merchantId;
private Long pwd;
private Long serverURL;
private Long processTimeout;
private Boolean enableAmountRound;

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

public Long getPwd() {
return pwd;
}

public void setPwd(Long  pwd) {
this.pwd = pwd;
}

public Long getServerURL() {
return serverURL;
}

public void setServerURL(Long  serverURL) {
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
