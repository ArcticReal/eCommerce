package com.skytala.eCommerce.domain.product.relations.productPromoRule.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.mapper.ProductPromoRuleMapper;

public class ProductPromoRule implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String productPromoRuleId;
private String ruleName;

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

public String getRuleName() {
return ruleName;
}

public void setRuleName(String  ruleName) {
this.ruleName = ruleName;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoRuleMapper.map(this);
}
}
