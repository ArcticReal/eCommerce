package com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.mapper.ShipmentItemFeatureMapper;

public class ShipmentItemFeature implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentItemSeqId;
private String productFeatureId;

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

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}


public Map<String, Object> mapAttributeField() {
return ShipmentItemFeatureMapper.map(this);
}
}
