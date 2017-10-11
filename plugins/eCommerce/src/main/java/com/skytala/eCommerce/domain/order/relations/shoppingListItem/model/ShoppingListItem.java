package com.skytala.eCommerce.domain.order.relations.shoppingListItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.shoppingListItem.mapper.ShoppingListItemMapper;

public class ShoppingListItem implements Serializable{

private static final long serialVersionUID = 1L;
private String shoppingListId;
private String shoppingListItemSeqId;
private String productId;
private BigDecimal quantity;
private BigDecimal modifiedPrice;
private Timestamp reservStart;
private BigDecimal reservLength;
private BigDecimal reservPersons;
private BigDecimal quantityPurchased;
private String configId;

public String getShoppingListId() {
return shoppingListId;
}

public void setShoppingListId(String  shoppingListId) {
this.shoppingListId = shoppingListId;
}

public String getShoppingListItemSeqId() {
return shoppingListItemSeqId;
}

public void setShoppingListItemSeqId(String  shoppingListItemSeqId) {
this.shoppingListItemSeqId = shoppingListItemSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getModifiedPrice() {
return modifiedPrice;
}

public void setModifiedPrice(BigDecimal  modifiedPrice) {
this.modifiedPrice = modifiedPrice;
}

public Timestamp getReservStart() {
return reservStart;
}

public void setReservStart(Timestamp  reservStart) {
this.reservStart = reservStart;
}

public BigDecimal getReservLength() {
return reservLength;
}

public void setReservLength(BigDecimal  reservLength) {
this.reservLength = reservLength;
}

public BigDecimal getReservPersons() {
return reservPersons;
}

public void setReservPersons(BigDecimal  reservPersons) {
this.reservPersons = reservPersons;
}

public BigDecimal getQuantityPurchased() {
return quantityPurchased;
}

public void setQuantityPurchased(BigDecimal  quantityPurchased) {
this.quantityPurchased = quantityPurchased;
}

public String getConfigId() {
return configId;
}

public void setConfigId(String  configId) {
this.configId = configId;
}


public Map<String, Object> mapAttributeField() {
return ShoppingListItemMapper.map(this);
}
}
