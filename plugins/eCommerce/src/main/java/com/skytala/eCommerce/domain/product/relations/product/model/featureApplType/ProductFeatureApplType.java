package com.skytala.eCommerce.domain.product.relations.product.model.featureApplType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplType.ProductFeatureApplTypeMapper;

public class ProductFeatureApplType implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureApplTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getProductFeatureApplTypeId() {
return productFeatureApplTypeId;
}

public void setProductFeatureApplTypeId(String  productFeatureApplTypeId) {
this.productFeatureApplTypeId = productFeatureApplTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureApplTypeMapper.map(this);
}
}
