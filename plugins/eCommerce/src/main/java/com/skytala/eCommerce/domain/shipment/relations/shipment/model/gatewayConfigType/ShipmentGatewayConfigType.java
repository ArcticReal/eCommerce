package com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfigType.ShipmentGatewayConfigTypeMapper;

public class ShipmentGatewayConfigType implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentGatewayConfTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getShipmentGatewayConfTypeId() {
return shipmentGatewayConfTypeId;
}

public void setShipmentGatewayConfTypeId(String  shipmentGatewayConfTypeId) {
this.shipmentGatewayConfTypeId = shipmentGatewayConfTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ShipmentGatewayConfigTypeMapper.map(this);
}
}
