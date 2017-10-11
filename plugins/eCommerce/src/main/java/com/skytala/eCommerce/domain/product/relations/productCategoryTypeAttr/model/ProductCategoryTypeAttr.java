package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.mapper.ProductCategoryTypeAttrMapper;

public class ProductCategoryTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryTypeId;
private String attrName;
private String description;

public String getProductCategoryTypeId() {
return productCategoryTypeId;
}

public void setProductCategoryTypeId(String  productCategoryTypeId) {
this.productCategoryTypeId = productCategoryTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryTypeAttrMapper.map(this);
}
}
