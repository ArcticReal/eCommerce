package com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureDataResource.ProductFeatureDataResourceMapper;

public class ProductFeatureDataResource implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private String productFeatureId;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureDataResourceMapper.map(this);
}
}
