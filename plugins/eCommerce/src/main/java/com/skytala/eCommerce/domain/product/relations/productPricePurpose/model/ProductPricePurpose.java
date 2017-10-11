package com.skytala.eCommerce.domain.product.relations.productPricePurpose.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productPricePurpose.mapper.ProductPricePurposeMapper;

public class ProductPricePurpose implements Serializable{

private static final long serialVersionUID = 1L;
private String productPricePurposeId;
private String description;

public String getProductPricePurposeId() {
return productPricePurposeId;
}

public void setProductPricePurposeId(String  productPricePurposeId) {
this.productPricePurposeId = productPricePurposeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductPricePurposeMapper.map(this);
}
}
