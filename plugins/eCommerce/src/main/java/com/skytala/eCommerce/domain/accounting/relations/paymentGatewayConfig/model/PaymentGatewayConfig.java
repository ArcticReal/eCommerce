package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfig.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfig.mapper.PaymentGatewayConfigMapper;

public class PaymentGatewayConfig implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String paymentGatewayConfigTypeId;
private String description;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getPaymentGatewayConfigTypeId() {
return paymentGatewayConfigTypeId;
}

public void setPaymentGatewayConfigTypeId(String  paymentGatewayConfigTypeId) {
this.paymentGatewayConfigTypeId = paymentGatewayConfigTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayConfigMapper.map(this);
}
}
