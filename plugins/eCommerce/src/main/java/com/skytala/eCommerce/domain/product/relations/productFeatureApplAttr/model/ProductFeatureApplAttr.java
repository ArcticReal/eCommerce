package com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.mapper.ProductFeatureApplAttrMapper;

public class ProductFeatureApplAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String productFeatureId;
private Timestamp fromDate;
private String attrName;
private Long attrValue;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
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


public Map<String, Object> mapAttributeField() {
return ProductFeatureApplAttrMapper.map(this);
}
}
