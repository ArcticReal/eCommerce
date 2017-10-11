package com.skytala.eCommerce.domain.product.relations.productCategoryType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productCategoryType.mapper.ProductCategoryTypeMapper;

public class ProductCategoryType implements Serializable{

private static final long serialVersionUID = 1L;
private String productCategoryTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getProductCategoryTypeId() {
return productCategoryTypeId;
}

public void setProductCategoryTypeId(String  productCategoryTypeId) {
this.productCategoryTypeId = productCategoryTypeId;
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
return ProductCategoryTypeMapper.map(this);
}
}
