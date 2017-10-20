package com.skytala.eCommerce.domain.product.relations.product.model.promoUse;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoUse.ProductPromoUseMapper;

public class ProductPromoUse implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String promoSequenceId;
private String productPromoId;
private String productPromoCodeId;
private String partyId;
private BigDecimal totalDiscountAmount;
private BigDecimal quantityLeftInActions;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getPromoSequenceId() {
return promoSequenceId;
}

public void setPromoSequenceId(String  promoSequenceId) {
this.promoSequenceId = promoSequenceId;
}

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public String getProductPromoCodeId() {
return productPromoCodeId;
}

public void setProductPromoCodeId(String  productPromoCodeId) {
this.productPromoCodeId = productPromoCodeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public BigDecimal getTotalDiscountAmount() {
return totalDiscountAmount;
}

public void setTotalDiscountAmount(BigDecimal  totalDiscountAmount) {
this.totalDiscountAmount = totalDiscountAmount;
}

public BigDecimal getQuantityLeftInActions() {
return quantityLeftInActions;
}

public void setQuantityLeftInActions(BigDecimal  quantityLeftInActions) {
this.quantityLeftInActions = quantityLeftInActions;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoUseMapper.map(this);
}
}
