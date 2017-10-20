package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryProduced.WorkEffortInventoryProducedMapper;

public class WorkEffortInventoryProduced implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String inventoryItemId;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortInventoryProducedMapper.map(this);
}
}
