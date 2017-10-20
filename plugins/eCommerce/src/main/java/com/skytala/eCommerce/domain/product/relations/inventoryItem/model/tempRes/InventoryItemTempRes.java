package com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.tempRes.InventoryItemTempResMapper;

public class InventoryItemTempRes implements Serializable{

private static final long serialVersionUID = 1L;
private String visitId;
private String productId;
private String productStoreId;
private BigDecimal quantity;
private Timestamp reservedDate;

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public Timestamp getReservedDate() {
return reservedDate;
}

public void setReservedDate(Timestamp  reservedDate) {
this.reservedDate = reservedDate;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemTempResMapper.map(this);
}
}
