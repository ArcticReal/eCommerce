package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.mapper.ShipmentContactMechTypeMapper;

public class ShipmentContactMechType implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentContactMechTypeId;
private String description;

public String getShipmentContactMechTypeId() {
return shipmentContactMechTypeId;
}

public void setShipmentContactMechTypeId(String  shipmentContactMechTypeId) {
this.shipmentContactMechTypeId = shipmentContactMechTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ShipmentContactMechTypeMapper.map(this);
}
}
