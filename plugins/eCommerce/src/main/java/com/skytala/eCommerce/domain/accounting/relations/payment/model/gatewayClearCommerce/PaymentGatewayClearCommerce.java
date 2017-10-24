package com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayClearCommerce;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayClearCommerce.PaymentGatewayClearCommerceMapper;

public class PaymentGatewayClearCommerce implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentGatewayConfigId;
private String sourceId;
private String groupId;
private String clientId;
private String username;
private String pwd;
private String userAlias;
private String effectiveAlias;
private Boolean processMode;
private String serverURL;
private Boolean enableCVM;

public String getPaymentGatewayConfigId() {
return paymentGatewayConfigId;
}

public void setPaymentGatewayConfigId(String  paymentGatewayConfigId) {
this.paymentGatewayConfigId = paymentGatewayConfigId;
}

public String getSourceId() {
return sourceId;
}

public void setSourceId(String  sourceId) {
this.sourceId = sourceId;
}

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
}

public String getClientId() {
return clientId;
}

public void setClientId(String  clientId) {
this.clientId = clientId;
}

public String getUsername() {
return username;
}

public void setUsername(String  username) {
this.username = username;
}

public String getPwd() {
return pwd;
}

public void setPwd(String  pwd) {
this.pwd = pwd;
}

public String getUserAlias() {
return userAlias;
}

public void setUserAlias(String  userAlias) {
this.userAlias = userAlias;
}

public String getEffectiveAlias() {
return effectiveAlias;
}

public void setEffectiveAlias(String  effectiveAlias) {
this.effectiveAlias = effectiveAlias;
}

public Boolean getProcessMode() {
return processMode;
}

public void setProcessMode(Boolean  processMode) {
this.processMode = processMode;
}

public String getServerURL() {
return serverURL;
}

public void setServerURL(String  serverURL) {
this.serverURL = serverURL;
}

public Boolean getEnableCVM() {
return enableCVM;
}

public void setEnableCVM(Boolean  enableCVM) {
this.enableCVM = enableCVM;
}


public Map<String, Object> mapAttributeField() {
return PaymentGatewayClearCommerceMapper.map(this);
}
}
