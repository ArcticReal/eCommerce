package com.skytala.eCommerce.domain.product.relations.product.model.featureIactn;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureIactn.ProductFeatureIactnMapper;

public class ProductFeatureIactn implements Serializable{

private static final long serialVersionUID = 1L;
private String productFeatureId;
private String productFeatureIdTo;
private String productFeatureIactnTypeId;
private String productId;

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getProductFeatureIdTo() {
return productFeatureIdTo;
}

public void setProductFeatureIdTo(String  productFeatureIdTo) {
this.productFeatureIdTo = productFeatureIdTo;
}

public String getProductFeatureIactnTypeId() {
return productFeatureIactnTypeId;
}

public void setProductFeatureIactnTypeId(String  productFeatureIactnTypeId) {
this.productFeatureIactnTypeId = productFeatureIactnTypeId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}


public Map<String, Object> mapAttributeField() {
return ProductFeatureIactnMapper.map(this);
}
}
