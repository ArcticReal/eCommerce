package com.skytala.eCommerce.domain.product.relations.productStoreGroupType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.mapper.ProductStoreGroupTypeMapper;

public class ProductStoreGroupType implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreGroupTypeId;
private String description;

public String getProductStoreGroupTypeId() {
return productStoreGroupTypeId;
}

public void setProductStoreGroupTypeId(String  productStoreGroupTypeId) {
this.productStoreGroupTypeId = productStoreGroupTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreGroupTypeMapper.map(this);
}
}
