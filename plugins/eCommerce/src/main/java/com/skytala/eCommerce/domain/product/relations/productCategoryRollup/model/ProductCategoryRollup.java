package com.skytala.eCommerce.domain.product.relations.productCategoryRollup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCategoryRollup.mapper.ProductCategoryRollupMapper;

public class ProductCategoryRollup implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String parentProductCategoryId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getParentProductCategoryId() {
return parentProductCategoryId;
}

public void setParentProductCategoryId(String  parentProductCategoryId) {
this.parentProductCategoryId = parentProductCategoryId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProductCategoryRollupMapper.map(this);
}
}
