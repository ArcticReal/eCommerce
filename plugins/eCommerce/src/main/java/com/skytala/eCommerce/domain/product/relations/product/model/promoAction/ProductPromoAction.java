package com.skytala.eCommerce.domain.product.relations.product.model.promoAction;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoAction.ProductPromoActionMapper;

public class ProductPromoAction implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String productPromoRuleId;
private String productPromoActionSeqId;
private String productPromoActionEnumId;
private String orderAdjustmentTypeId;
private String serviceName;
private BigDecimal quantity;
private BigDecimal amount;
private String productId;
private String partyId;
private Boolean useCartQuantity;

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}

public String getProductPromoRuleId() {
return productPromoRuleId;
}

public void setProductPromoRuleId(String  productPromoRuleId) {
this.productPromoRuleId = productPromoRuleId;
}

public String getProductPromoActionSeqId() {
return productPromoActionSeqId;
}

public void setProductPromoActionSeqId(String  productPromoActionSeqId) {
this.productPromoActionSeqId = productPromoActionSeqId;
}

public String getProductPromoActionEnumId() {
return productPromoActionEnumId;
}

public void setProductPromoActionEnumId(String  productPromoActionEnumId) {
this.productPromoActionEnumId = productPromoActionEnumId;
}

public String getOrderAdjustmentTypeId() {
return orderAdjustmentTypeId;
}

public void setOrderAdjustmentTypeId(String  orderAdjustmentTypeId) {
this.orderAdjustmentTypeId = orderAdjustmentTypeId;
}

public String getServiceName() {
return serviceName;
}

public void setServiceName(String  serviceName) {
this.serviceName = serviceName;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Boolean getUseCartQuantity() {
return useCartQuantity;
}

public void setUseCartQuantity(Boolean  useCartQuantity) {
this.useCartQuantity = useCartQuantity;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoActionMapper.map(this);
}
}
