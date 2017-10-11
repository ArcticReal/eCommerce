package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.mapper.ProductFacilityLocationMapper;

public class ProductFacilityLocation implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String facilityId;
private String locationSeqId;
private BigDecimal minimumStock;
private BigDecimal moveQuantity;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getLocationSeqId() {
return locationSeqId;
}

public void setLocationSeqId(String  locationSeqId) {
this.locationSeqId = locationSeqId;
}

public BigDecimal getMinimumStock() {
return minimumStock;
}

public void setMinimumStock(BigDecimal  minimumStock) {
this.minimumStock = minimumStock;
}

public BigDecimal getMoveQuantity() {
return moveQuantity;
}

public void setMoveQuantity(BigDecimal  moveQuantity) {
this.moveQuantity = moveQuantity;
}


public Map<String, Object> mapAttributeField() {
return ProductFacilityLocationMapper.map(this);
}
}
