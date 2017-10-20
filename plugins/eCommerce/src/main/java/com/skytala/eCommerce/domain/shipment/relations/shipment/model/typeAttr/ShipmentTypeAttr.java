package com.skytala.eCommerce.domain.shipment.relations.shipment.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.typeAttr.ShipmentTypeAttrMapper;

public class ShipmentTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentTypeId;
private String attrName;
private String description;

public String getShipmentTypeId() {
return shipmentTypeId;
}

public void setShipmentTypeId(String  shipmentTypeId) {
this.shipmentTypeId = shipmentTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ShipmentTypeAttrMapper.map(this);
}
}
