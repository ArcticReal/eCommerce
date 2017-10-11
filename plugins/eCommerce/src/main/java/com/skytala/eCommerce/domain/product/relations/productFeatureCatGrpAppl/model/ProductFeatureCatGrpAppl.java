package com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.mapper.ProductFeatureCatGrpApplMapper;

public class ProductFeatureCatGrpAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryId;
private String productFeatureGroupId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getProductFeatureGroupId() {
return productFeatureGroupId;
}

public void setProductFeatureGroupId(String  productFeatureGroupId) {
this.productFeatureGroupId = productFeatureGroupId;
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


public Map<String, Object> mapAttributeField() {
return ProductFeatureCatGrpApplMapper.map(this);
}
}
