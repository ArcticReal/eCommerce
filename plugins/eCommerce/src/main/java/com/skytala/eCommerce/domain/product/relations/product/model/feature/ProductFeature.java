package com.skytala.eCommerce.domain.product.relations.product.model.feature;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.feature.ProductFeatureMapper;

public class ProductFeature implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureId;
private String productFeatureTypeId;
private String productFeatureCategoryId;
private String description;
private String uomId;
private BigDecimal numberSpecified;
private BigDecimal defaultAmount;
private Long defaultSequenceNum;
private String abbrev;
private String idCode;

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getProductFeatureTypeId() {
return productFeatureTypeId;
}

public void setProductFeatureTypeId(String  productFeatureTypeId) {
this.productFeatureTypeId = productFeatureTypeId;
}

public String getProductFeatureCategoryId() {
return productFeatureCategoryId;
}

public void setProductFeatureCategoryId(String  productFeatureCategoryId) {
this.productFeatureCategoryId = productFeatureCategoryId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public BigDecimal getNumberSpecified() {
return numberSpecified;
}

public void setNumberSpecified(BigDecimal  numberSpecified) {
this.numberSpecified = numberSpecified;
}

public BigDecimal getDefaultAmount() {
return defaultAmount;
}

public void setDefaultAmount(BigDecimal  defaultAmount) {
this.defaultAmount = defaultAmount;
}

public Long getDefaultSequenceNum() {
return defaultSequenceNum;
}

public void setDefaultSequenceNum(Long  defaultSequenceNum) {
this.defaultSequenceNum = defaultSequenceNum;
}

public String getAbbrev() {
return abbrev;
}

public void setAbbrev(String  abbrev) {
this.abbrev = abbrev;
}

public String getIdCode() {
return idCode;
}

public void setIdCode(String  idCode) {
this.idCode = idCode;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureMapper.map(this);
}
}
