package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.mapper.ProductFeatureIactnTypeMapper;

public class ProductFeatureIactnType implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureIactnTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getProductFeatureIactnTypeId() {
return productFeatureIactnTypeId;
}

public void setProductFeatureIactnTypeId(String  productFeatureIactnTypeId) {
this.productFeatureIactnTypeId = productFeatureIactnTypeId;
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
return ProductFeatureIactnTypeMapper.map(this);
}
}
