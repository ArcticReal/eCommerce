package com.skytala.eCommerce.domain.shipment.relations.shipmentType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentType.mapper.ShipmentTypeMapper;

public class ShipmentType implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getShipmentTypeId() {
return shipmentTypeId;
}

public void setShipmentTypeId(String  shipmentTypeId) {
this.shipmentTypeId = shipmentTypeId;
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
return ShipmentTypeMapper.map(this);
}
}
