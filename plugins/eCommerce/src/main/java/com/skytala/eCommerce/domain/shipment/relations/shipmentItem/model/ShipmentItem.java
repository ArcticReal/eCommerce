package com.skytala.eCommerce.domain.shipment.relations.shipmentItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.mapper.ShipmentItemMapper;

public class ShipmentItem implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentItemSeqId;
private String productId;
private BigDecimal quantity;
private String shipmentContentDescription;

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentItemSeqId() {
return shipmentItemSeqId;
}

public void setShipmentItemSeqId(String  shipmentItemSeqId) {
this.shipmentItemSeqId = shipmentItemSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public String getShipmentContentDescription() {
return shipmentContentDescription;
}

public void setShipmentContentDescription(String  shipmentContentDescription) {
this.shipmentContentDescription = shipmentContentDescription;
}


public Map<String, Object> mapAttributeField() {
return ShipmentItemMapper.map(this);
}
}
