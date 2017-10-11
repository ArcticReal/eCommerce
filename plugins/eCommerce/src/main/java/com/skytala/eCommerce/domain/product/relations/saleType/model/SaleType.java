package com.skytala.eCommerce.domain.product.relations.saleType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.saleType.mapper.SaleTypeMapper;

public class SaleType implements Serializable{

private static final long serialVersionUID = 1L;
private String saleTypeId;
private String description;

public String getSaleTypeId() {
return saleTypeId;
}

public void setSaleTypeId(String  saleTypeId) {
this.saleTypeId = saleTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SaleTypeMapper.map(this);
}
}
