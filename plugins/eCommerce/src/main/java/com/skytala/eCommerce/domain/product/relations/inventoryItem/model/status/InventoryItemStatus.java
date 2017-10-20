package com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.status.InventoryItemStatusMapper;

public class InventoryItemStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemId;
private String statusId;
private Timestamp statusDatetime;
private Timestamp statusEndDatetime;
private String changeByUserLoginId;
private String ownerPartyId;
private String productId;

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getStatusDatetime() {
return statusDatetime;
}

public void setStatusDatetime(Timestamp  statusDatetime) {
this.statusDatetime = statusDatetime;
}

public Timestamp getStatusEndDatetime() {
return statusEndDatetime;
}

public void setStatusEndDatetime(Timestamp  statusEndDatetime) {
this.statusEndDatetime = statusEndDatetime;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}

public String getOwnerPartyId() {
return ownerPartyId;
}

public void setOwnerPartyId(String  ownerPartyId) {
this.ownerPartyId = ownerPartyId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemStatusMapper.map(this);
}
}
