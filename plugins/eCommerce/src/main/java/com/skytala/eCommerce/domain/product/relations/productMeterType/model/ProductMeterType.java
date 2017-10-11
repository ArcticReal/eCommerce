package com.skytala.eCommerce.domain.product.relations.productMeterType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productMeterType.mapper.ProductMeterTypeMapper;

public class ProductMeterType implements Serializable{

private static final long serialVersionUID = 1L;
private String productMeterTypeId;
private String description;
private String defaultUomId;

public String getProductMeterTypeId() {
return productMeterTypeId;
}

public void setProductMeterTypeId(String  productMeterTypeId) {
this.productMeterTypeId = productMeterTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getDefaultUomId() {
return defaultUomId;
}

public void setDefaultUomId(String  defaultUomId) {
this.defaultUomId = defaultUomId;
}


public Map<String, Object> mapAttributeField() {
return ProductMeterTypeMapper.map(this);
}
}
