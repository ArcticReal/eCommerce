package com.skytala.eCommerce.domain.productFeatureCategory.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.productFeatureCategory.mapper.ProductFeatureCategoryMapper;

public class ProductFeatureCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureCategoryId;
private String parentCategoryId;
private String description;

public String getProductFeatureCategoryId() {
return productFeatureCategoryId;
}

public void setProductFeatureCategoryId(String  productFeatureCategoryId) {
this.productFeatureCategoryId = productFeatureCategoryId;
}

public String getParentCategoryId() {
return parentCategoryId;
}

public void setParentCategoryId(String  parentCategoryId) {
this.parentCategoryId = parentCategoryId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureCategoryMapper.map(this);
}
}
