package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.mapper.PaymentGatewayRespMsgMapper;

public class PaymentGatewayRespMsg implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayRespMsgId;
private String paymentGatewayResponseId;
private String pgrMessage;

public String getPaymentGatewayRespMsgId() {
return paymentGatewayRespMsgId;
}

public void setPaymentGatewayRespMsgId(String  paymentGatewayRespMsgId) {
this.paymentGatewayRespMsgId = paymentGatewayRespMsgId;
}

public String getPaymentGatewayResponseId() {
return paymentGatewayResponseId;
}

public void setPaymentGatewayResponseId(String  paymentGatewayResponseId) {
this.paymentGatewayResponseId = paymentGatewayResponseId;
}

public String getPgrMessage() {
return pgrMessage;
}

public void setPgrMessage(String  pgrMessage) {
this.pgrMessage = pgrMessage;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayRespMsgMapper.map(this);
}
}
