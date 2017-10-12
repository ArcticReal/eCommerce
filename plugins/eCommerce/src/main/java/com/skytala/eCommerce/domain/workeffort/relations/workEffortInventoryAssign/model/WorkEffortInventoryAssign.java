package com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortInventoryAssign.mapper.WorkEffortInventoryAssignMapper;

public class WorkEffortInventoryAssign implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String inventoryItemId;
private String statusId;
private BigDecimal quantity;

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

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortInventoryAssignMapper.map(this);
}
}
