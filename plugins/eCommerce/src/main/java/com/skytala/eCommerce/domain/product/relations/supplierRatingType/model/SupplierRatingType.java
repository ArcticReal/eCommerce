package com.skytala.eCommerce.domain.product.relations.supplierRatingType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.mapper.SupplierRatingTypeMapper;

public class SupplierRatingType implements Serializable{

private static final long serialVersionUID = 1L;
private String supplierRatingTypeId;
private String description;

public String getSupplierRatingTypeId() {
return supplierRatingTypeId;
}

public void setSupplierRatingTypeId(String  supplierRatingTypeId) {
this.supplierRatingTypeId = supplierRatingTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SupplierRatingTypeMapper.map(this);
}
}
