package com.skytala.eCommerce.domain.product.relations.productPriceActionType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPriceActionType.mapper.ProductPriceActionTypeMapper;

public class ProductPriceActionType implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceActionTypeId;
private String description;

public String getProductPriceActionTypeId() {
return productPriceActionTypeId;
}

public void setProductPriceActionTypeId(String  productPriceActionTypeId) {
this.productPriceActionTypeId = productPriceActionTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductPriceActionTypeMapper.map(this);
}
}
