package com.skytala.eCommerce.domain.product.relations.facility.model.product;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.product.ProductFacilityMapper;

public class ProductFacility implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String facilityId;
private BigDecimal minimumStock;
private BigDecimal reorderQuantity;
private Long daysToShip;
private BigDecimal lastInventoryCount;

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

public BigDecimal getMinimumStock() {
return minimumStock;
}

public void setMinimumStock(BigDecimal  minimumStock) {
this.minimumStock = minimumStock;
}

public BigDecimal getReorderQuantity() {
return reorderQuantity;
}

public void setReorderQuantity(BigDecimal  reorderQuantity) {
this.reorderQuantity = reorderQuantity;
}

public Long getDaysToShip() {
return daysToShip;
}

public void setDaysToShip(Long  daysToShip) {
this.daysToShip = daysToShip;
}

public BigDecimal getLastInventoryCount() {
return lastInventoryCount;
}

public void setLastInventoryCount(BigDecimal  lastInventoryCount) {
this.lastInventoryCount = lastInventoryCount;
}


public Map<String, Object> mapAttributeField() {
return ProductFacilityMapper.map(this);
}
}
