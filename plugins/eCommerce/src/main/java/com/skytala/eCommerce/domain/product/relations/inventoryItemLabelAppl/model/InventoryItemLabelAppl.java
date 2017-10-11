package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.mapper.InventoryItemLabelApplMapper;

public class InventoryItemLabelAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemId;
private String inventoryItemLabelTypeId;
private String inventoryItemLabelId;
private Long sequenceNum;

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getInventoryItemLabelTypeId() {
return inventoryItemLabelTypeId;
}

public void setInventoryItemLabelTypeId(String  inventoryItemLabelTypeId) {
this.inventoryItemLabelTypeId = inventoryItemLabelTypeId;
}

public String getInventoryItemLabelId() {
return inventoryItemLabelId;
}

public void setInventoryItemLabelId(String  inventoryItemLabelId) {
this.inventoryItemLabelId = inventoryItemLabelId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemLabelApplMapper.map(this);
}
}
