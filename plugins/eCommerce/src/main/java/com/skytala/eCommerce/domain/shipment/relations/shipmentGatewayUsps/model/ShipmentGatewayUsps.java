package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.mapper.ShipmentGatewayUspsMapper;

public class ShipmentGatewayUsps implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private Long connectUrl;
private Long connectUrlLabels;
private Long connectTimeout;
private Long accessUserId;
private Long accessPassword;
private Long maxEstimateWeight;
private String test;

public String getShipmentGatewayConfigId() {
return shipmentGatewayConfigId;
}

public void setShipmentGatewayConfigId(String  shipmentGatewayConfigId) {
this.shipmentGatewayConfigId = shipmentGatewayConfigId;
}

public Long getConnectUrl() {
return connectUrl;
}

public void setConnectUrl(Long  connectUrl) {
this.connectUrl = connectUrl;
}

public Long getConnectUrlLabels() {
return connectUrlLabels;
}

public void setConnectUrlLabels(Long  connectUrlLabels) {
this.connectUrlLabels = connectUrlLabels;
}

public Long getConnectTimeout() {
return connectTimeout;
}

public void setConnectTimeout(Long  connectTimeout) {
this.connectTimeout = connectTimeout;
}

public Long getAccessUserId() {
return accessUserId;
}

public void setAccessUserId(Long  accessUserId) {
this.accessUserId = accessUserId;
}

public Long getAccessPassword() {
return accessPassword;
}

public void setAccessPassword(Long  accessPassword) {
this.accessPassword = accessPassword;
}

public Long getMaxEstimateWeight() {
return maxEstimateWeight;
}

public void setMaxEstimateWeight(Long  maxEstimateWeight) {
this.maxEstimateWeight = maxEstimateWeight;
}

public String getTest() {
return test;
}

public void setTest(String  test) {
this.test = test;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayUspsMapper.map(this);
}
}
