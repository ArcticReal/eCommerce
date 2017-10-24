package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayEway;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayEway.PaymentGatewayEwayMapper;

public class PaymentGatewayEway implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String customerId;
private String refundPwd;
private String testMode;
private String enableCvn;
private String enableBeagle;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getCustomerId() {
return customerId;
}

public void setCustomerId(String  customerId) {
this.customerId = customerId;
}

public String getRefundPwd() {
return refundPwd;
}

public void setRefundPwd(String  refundPwd) {
this.refundPwd = refundPwd;
}

public String getTestMode() {
return testMode;
}

public void setTestMode(String  testMode) {
this.testMode = testMode;
}

public String getEnableCvn() {
return enableCvn;
}

public void setEnableCvn(String  enableCvn) {
this.enableCvn = enableCvn;
}

public String getEnableBeagle() {
return enableBeagle;
}

public void setEnableBeagle(String  enableBeagle) {
this.enableBeagle = enableBeagle;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayEwayMapper.map(this);
}
}
