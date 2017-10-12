package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.mapper.ShippingDocumentMapper;

public class ShippingDocument implements Serializable{

private static final long serialVersionUID = 1L;
private String documentId;
private String shipmentId;
private String shipmentItemSeqId;
private String shipmentPackageSeqId;
private String description;

public String getDocumentId() {
return documentId;
}

public void setDocumentId(String  documentId) {
this.documentId = documentId;
}

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

public String getShipmentPackageSeqId() {
return shipmentPackageSeqId;
}

public void setShipmentPackageSeqId(String  shipmentPackageSeqId) {
this.shipmentPackageSeqId = shipmentPackageSeqId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ShippingDocumentMapper.map(this);
}
}
