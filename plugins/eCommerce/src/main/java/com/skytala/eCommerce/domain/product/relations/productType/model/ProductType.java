package com.skytala.eCommerce.domain.product.relations.productType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productType.mapper.ProductTypeMapper;

public class ProductType implements Serializable{

private static final long serialVersionUID = 1L;
private String productTypeId;
private String parentTypeId;
private Boolean isPhysical;
private Boolean isDigital;
private Boolean hasTable;
private String description;

public String getProductTypeId() {
return productTypeId;
}

public void setProductTypeId(String  productTypeId) {
this.productTypeId = productTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getIsPhysical() {
return isPhysical;
}

public void setIsPhysical(Boolean  isPhysical) {
this.isPhysical = isPhysical;
}

public Boolean getIsDigital() {
return isDigital;
}

public void setIsDigital(Boolean  isDigital) {
this.isDigital = isDigital;
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
return ProductTypeMapper.map(this);
}
}
