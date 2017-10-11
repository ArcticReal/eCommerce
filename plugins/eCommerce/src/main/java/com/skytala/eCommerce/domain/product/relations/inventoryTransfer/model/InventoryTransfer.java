package com.skytala.eCommerce.domain.product.relations.inventoryTransfer.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.mapper.InventoryTransferMapper;

public class InventoryTransfer implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryTransferId;
private String statusId;
private String inventoryItemId;
private String facilityId;
private String locationSeqId;
private String containerId;
private String facilityIdTo;
private String locationSeqIdTo;
private String containerIdTo;
private String itemIssuanceId;
private Timestamp sendDate;
private Timestamp receiveDate;
private String comments;

public String getInventoryTransferId() {
return inventoryTransferId;
}

public void setInventoryTransferId(String  inventoryTransferId) {
this.inventoryTransferId = inventoryTransferId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getLocationSeqId() {
return locationSeqId;
}

public void setLocationSeqId(String  locationSeqId) {
this.locationSeqId = locationSeqId;
}

public String getContainerId() {
return containerId;
}

public void setContainerId(String  containerId) {
this.containerId = containerId;
}

public String getFacilityIdTo() {
return facilityIdTo;
}

public void setFacilityIdTo(String  facilityIdTo) {
this.facilityIdTo = facilityIdTo;
}

public String getLocationSeqIdTo() {
return locationSeqIdTo;
}

public void setLocationSeqIdTo(String  locationSeqIdTo) {
this.locationSeqIdTo = locationSeqIdTo;
}

public String getContainerIdTo() {
return containerIdTo;
}

public void setContainerIdTo(String  containerIdTo) {
this.containerIdTo = containerIdTo;
}

public String getItemIssuanceId() {
return itemIssuanceId;
}

public void setItemIssuanceId(String  itemIssuanceId) {
this.itemIssuanceId = itemIssuanceId;
}

public Timestamp getSendDate() {
return sendDate;
}

public void setSendDate(Timestamp  sendDate) {
this.sendDate = sendDate;
}

public Timestamp getReceiveDate() {
return receiveDate;
}

public void setReceiveDate(Timestamp  receiveDate) {
this.receiveDate = receiveDate;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return InventoryTransferMapper.map(this);
}
}
