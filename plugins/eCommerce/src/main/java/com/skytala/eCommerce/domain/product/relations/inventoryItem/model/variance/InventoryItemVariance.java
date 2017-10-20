package com.skytala.eCommerce.domain.product.relations.inventoryItem.model.variance;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.variance.InventoryItemVarianceMapper;

public class InventoryItemVariance implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemId;
private String physicalInventoryId;
private String varianceReasonId;
private BigDecimal availableToPromiseVar;
private BigDecimal quantityOnHandVar;
private String comments;

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getPhysicalInventoryId() {
return physicalInventoryId;
}

public void setPhysicalInventoryId(String  physicalInventoryId) {
this.physicalInventoryId = physicalInventoryId;
}

public String getVarianceReasonId() {
return varianceReasonId;
}

public void setVarianceReasonId(String  varianceReasonId) {
this.varianceReasonId = varianceReasonId;
}

public BigDecimal getAvailableToPromiseVar() {
return availableToPromiseVar;
}

public void setAvailableToPromiseVar(BigDecimal  availableToPromiseVar) {
this.availableToPromiseVar = availableToPromiseVar;
}

public BigDecimal getQuantityOnHandVar() {
return quantityOnHandVar;
}

public void setQuantityOnHandVar(BigDecimal  quantityOnHandVar) {
this.quantityOnHandVar = quantityOnHandVar;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemVarianceMapper.map(this);
}
}
