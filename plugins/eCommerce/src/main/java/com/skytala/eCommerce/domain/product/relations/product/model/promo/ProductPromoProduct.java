package com.skytala.eCommerce.domain.product.relations.product.model.promo;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoProductMapper;

public class ProductPromoProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String productPromoRuleId;
private String productPromoActionSeqId;
private String productPromoCondSeqId;
private String productId;
private String productPromoApplEnumId;

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

public String getProductPromoCondSeqId() {
return productPromoCondSeqId;
}

public void setProductPromoCondSeqId(String  productPromoCondSeqId) {
this.productPromoCondSeqId = productPromoCondSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductPromoApplEnumId() {
return productPromoApplEnumId;
}

public void setProductPromoApplEnumId(String  productPromoApplEnumId) {
this.productPromoApplEnumId = productPromoApplEnumId;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoProductMapper.map(this);
}
}
