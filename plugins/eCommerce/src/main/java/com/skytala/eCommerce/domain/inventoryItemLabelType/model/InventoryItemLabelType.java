package com.skytala.eCommerce.domain.inventoryItemLabelType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.inventoryItemLabelType.mapper.InventoryItemLabelTypeMapper;

public class InventoryItemLabelType implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemLabelTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getInventoryItemLabelTypeId() {
return inventoryItemLabelTypeId;
}

public void setInventoryItemLabelTypeId(String  inventoryItemLabelTypeId) {
this.inventoryItemLabelTypeId = inventoryItemLabelTypeId;
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
return InventoryItemLabelTypeMapper.map(this);
}
}
