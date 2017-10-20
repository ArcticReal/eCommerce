package com.skytala.eCommerce.domain.product.relations.product.model.priceType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceType.ProductPriceTypeMapper;

public class ProductPriceType implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceTypeId;
private String description;

public String getProductPriceTypeId() {
return productPriceTypeId;
}

public void setProductPriceTypeId(String  productPriceTypeId) {
this.productPriceTypeId = productPriceTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductPriceTypeMapper.map(this);
}
}
