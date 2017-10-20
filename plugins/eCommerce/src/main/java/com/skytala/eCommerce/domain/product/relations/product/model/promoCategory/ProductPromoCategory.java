package com.skytala.eCommerce.domain.product.relations.product.model.promoCategory;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCategory.ProductPromoCategoryMapper;

public class ProductPromoCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoId;
private String productPromoRuleId;
private String productPromoActionSeqId;
private String productPromoCondSeqId;
private String productCategoryId;
private String andGroupId;
private String productPromoApplEnumId;
private Boolean includeSubCategories;

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

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getAndGroupId() {
return andGroupId;
}

public void setAndGroupId(String  andGroupId) {
this.andGroupId = andGroupId;
}

public String getProductPromoApplEnumId() {
return productPromoApplEnumId;
}

public void setProductPromoApplEnumId(String  productPromoApplEnumId) {
this.productPromoApplEnumId = productPromoApplEnumId;
}

public Boolean getIncludeSubCategories() {
return includeSubCategories;
}

public void setIncludeSubCategories(Boolean  includeSubCategories) {
this.includeSubCategories = includeSubCategories;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoCategoryMapper.map(this);
}
}
