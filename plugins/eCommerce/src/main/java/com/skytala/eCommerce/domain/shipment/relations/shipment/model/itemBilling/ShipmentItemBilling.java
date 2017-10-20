package com.skytala.eCommerce.domain.shipment.relations.shipment.model.itemBilling;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.itemBilling.ShipmentItemBillingMapper;

public class ShipmentItemBilling implements Serializable{

private static final long serialVersionUID = 1L;
private String shipmentId;
private String shipmentItemSeqId;
private String invoiceId;
private String invoiceItemSeqId;

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

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceItemSeqId() {
return invoiceItemSeqId;
}

public void setInvoiceItemSeqId(String  invoiceItemSeqId) {
this.invoiceItemSeqId = invoiceItemSeqId;
}


public Map<String, Object> mapAttributeField() {
return ShipmentItemBillingMapper.map(this);
}
}
