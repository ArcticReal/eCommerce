package com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.label.InventoryItemLabelMapper;

public class InventoryItemLabel implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemLabelId;
private String inventoryItemLabelTypeId;
private String description;

public String getInventoryItemLabelId() {
return inventoryItemLabelId;
}

public void setInventoryItemLabelId(String  inventoryItemLabelId) {
this.inventoryItemLabelId = inventoryItemLabelId;
}

public String getInventoryItemLabelTypeId() {
return inventoryItemLabelTypeId;
}

public void setInventoryItemLabelTypeId(String  inventoryItemLabelTypeId) {
this.inventoryItemLabelTypeId = inventoryItemLabelTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemLabelMapper.map(this);
}
}
