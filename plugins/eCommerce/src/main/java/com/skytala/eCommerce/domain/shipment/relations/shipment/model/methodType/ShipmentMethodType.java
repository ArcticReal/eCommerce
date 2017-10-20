package com.skytala.eCommerce.domain.shipment.relations.shipment.model.methodType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.methodType.ShipmentMethodTypeMapper;

public class ShipmentMethodType implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentMethodTypeId;
private String description;
private Long sequenceNum;

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ShipmentMethodTypeMapper.map(this);
}
}
