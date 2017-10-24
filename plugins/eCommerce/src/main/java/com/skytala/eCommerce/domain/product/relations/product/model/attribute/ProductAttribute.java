package com.skytala.eCommerce.domain.product.relations.product.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.attribute.ProductAttributeMapper;

public class ProductAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String attrName;
private String attrValue;
private String attrType;
private String attrDescription;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getAttrValue() {
return attrValue;
}

public void setAttrValue(String  attrValue) {
this.attrValue = attrValue;
}

public String getAttrType() {
return attrType;
}

public void setAttrType(String  attrType) {
this.attrType = attrType;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return ProductAttributeMapper.map(this);
}
}
