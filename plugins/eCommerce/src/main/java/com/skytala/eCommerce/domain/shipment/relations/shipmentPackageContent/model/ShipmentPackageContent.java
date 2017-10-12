package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.mapper.ShipmentPackageContentMapper;

public class ShipmentPackageContent implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentPackageSeqId;
private String shipmentItemSeqId;
private BigDecimal quantity;
private String subProductId;
private BigDecimal subProductQuantity;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentPackageSeqId() {
return shipmentPackageSeqId;
}

public void setShipmentPackageSeqId(String  shipmentPackageSeqId) {
this.shipmentPackageSeqId = shipmentPackageSeqId;
}

public String getShipmentItemSeqId() {
return shipmentItemSeqId;
}

public void setShipmentItemSeqId(String  shipmentItemSeqId) {
this.shipmentItemSeqId = shipmentItemSeqId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public String getSubProductId() {
return subProductId;
}

public void setSubProductId(String  subProductId) {
this.subProductId = subProductId;
}

public BigDecimal getSubProductQuantity() {
return subProductQuantity;
}

public void setSubProductQuantity(BigDecimal  subProductQuantity) {
this.subProductQuantity = subProductQuantity;
}


public Map<String, Object> mapAttributeField() {
return ShipmentPackageContentMapper.map(this);
}
}
