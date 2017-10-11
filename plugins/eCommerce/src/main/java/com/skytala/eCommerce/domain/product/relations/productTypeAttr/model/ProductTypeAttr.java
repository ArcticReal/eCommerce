package com.skytala.eCommerce.domain.product.relations.productTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productTypeAttr.mapper.ProductTypeAttrMapper;

public class ProductTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String productTypeId;
private String attrName;
private String description;

public String getProductTypeId() {
return productTypeId;
}

public void setProductTypeId(String  productTypeId) {
this.productTypeId = productTypeId;
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
return ProductTypeAttrMapper.map(this);
}
}
