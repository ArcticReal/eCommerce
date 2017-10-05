package com.skytala.eCommerce.domain.productFeatureGroup.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productFeatureGroup.mapper.ProductFeatureGroupMapper;

public class ProductFeatureGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureGroupId;
private String description;

public String getProductFeatureGroupId() {
return productFeatureGroupId;
}

public void setProductFeatureGroupId(String  productFeatureGroupId) {
this.productFeatureGroupId = productFeatureGroupId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureGroupMapper.map(this);
}
}
