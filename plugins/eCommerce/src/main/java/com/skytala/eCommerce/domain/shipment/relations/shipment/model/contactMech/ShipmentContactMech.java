package com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.contactMech.ShipmentContactMechMapper;

public class ShipmentContactMech implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentContactMechTypeId;
private String contactMechId;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentContactMechTypeId() {
return shipmentContactMechTypeId;
}

public void setShipmentContactMechTypeId(String  shipmentContactMechTypeId) {
this.shipmentContactMechTypeId = shipmentContactMechTypeId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}


public Map<String, Object> mapAttributeField() {
return ShipmentContactMechMapper.map(this);
}
}
