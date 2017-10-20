package com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfig.ShipmentGatewayConfigMapper;

public class ShipmentGatewayConfig implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfigId;
private String shipmentGatewayConfTypeId;
private String description;

public String getShipmentGatewayConfigId() {
return shipmentGatewayConfigId;
}

public void setShipmentGatewayConfigId(String  shipmentGatewayConfigId) {
this.shipmentGatewayConfigId = shipmentGatewayConfigId;
}

public String getShipmentGatewayConfTypeId() {
return shipmentGatewayConfTypeId;
}

public void setShipmentGatewayConfTypeId(String  shipmentGatewayConfTypeId) {
this.shipmentGatewayConfTypeId = shipmentGatewayConfTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayConfigMapper.map(this);
}
}
