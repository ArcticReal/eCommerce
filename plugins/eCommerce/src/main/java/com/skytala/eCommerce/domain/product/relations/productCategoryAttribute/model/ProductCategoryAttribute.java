package com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCategoryAttribute.mapper.ProductCategoryAttributeMapper;

public class ProductCategoryAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public Long getAttrValue() {
return attrValue;
}

public void setAttrValue(Long  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryAttributeMapper.map(this);
}
}
