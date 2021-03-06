package com.skytala.eCommerce.domain.product.relations.inventoryItem.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.type.InventoryItemTypeMapper;

public class InventoryItemType implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getInventoryItemTypeId() {
return inventoryItemTypeId;
}

public void setInventoryItemTypeId(String  inventoryItemTypeId) {
this.inventoryItemTypeId = inventoryItemTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemTypeMapper.map(this);
}
}
