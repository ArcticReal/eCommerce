package com.skytala.eCommerce.domain.product.relations.productGeo.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productGeo.mapper.ProductGeoMapper;

public class ProductGeo implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String geoId;
private String productGeoEnumId;
private String description;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
}

public String getProductGeoEnumId() {
return productGeoEnumId;
}

public void setProductGeoEnumId(String  productGeoEnumId) {
this.productGeoEnumId = productGeoEnumId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductGeoMapper.map(this);
}
}
