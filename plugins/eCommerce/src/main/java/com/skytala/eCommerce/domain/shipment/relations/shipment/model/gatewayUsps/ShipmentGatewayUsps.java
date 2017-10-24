package com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUsps;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUsps.ShipmentGatewayUspsMapper;

public class ShipmentGatewayUsps implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private String connectUrl;
private String connectUrlLabels;
private Long connectTimeout;
private String accessUserId;
private String accessPassword;
private Long maxEstimateWeight;
private String test;

public String getShipmentGatewayConfigId() {
return shipmentGatewayConfigId;
}

public void setShipmentGatewayConfigId(String  shipmentGatewayConfigId) {
this.shipmentGatewayConfigId = shipmentGatewayConfigId;
}

public String getConnectUrl() {
return connectUrl;
}

public void setConnectUrl(String  connectUrl) {
this.connectUrl = connectUrl;
}

public String getConnectUrlLabels() {
return connectUrlLabels;
}

public void setConnectUrlLabels(String  connectUrlLabels) {
this.connectUrlLabels = connectUrlLabels;
}

public Long getConnectTimeout() {
return connectTimeout;
}

public void setConnectTimeout(Long  connectTimeout) {
this.connectTimeout = connectTimeout;
}

public String getAccessUserId() {
return accessUserId;
}

public void setAccessUserId(String  accessUserId) {
this.accessUserId = accessUserId;
}

public String getAccessPassword() {
return accessPassword;
}

public void setAccessPassword(String  accessPassword) {
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
