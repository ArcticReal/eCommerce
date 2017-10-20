package com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.typeAttr.InventoryItemTypeAttrMapper;

public class InventoryItemTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemTypeId;
private String attrName;
private String description;

public String getInventoryItemTypeId() {
return inventoryItemTypeId;
}

public void setInventoryItemTypeId(String  inventoryItemTypeId) {
this.inventoryItemTypeId = inventoryItemTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemTypeAttrMapper.map(this);
}
}
