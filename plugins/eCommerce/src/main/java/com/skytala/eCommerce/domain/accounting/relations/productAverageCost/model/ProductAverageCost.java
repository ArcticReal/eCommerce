package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper.ProductAverageCostMapper;

public class ProductAverageCost implements Serializable{

private static final long serialVersionUID = 1L;
private String productAverageCostTypeId;
private String organizationPartyId;
private String productId;
private String facilityId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal averageCost;

public String getProductAverageCostTypeId() {
return productAverageCostTypeId;
}

public void setProductAverageCostTypeId(String  productAverageCostTypeId) {
this.productAverageCostTypeId = productAverageCostTypeId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

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

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public BigDecimal getAverageCost() {
return averageCost;
}

public void setAverageCost(BigDecimal  averageCost) {
this.averageCost = averageCost;
}


public Map<String, Object> mapAttributeField() {
return ProductAverageCostMapper.map(this);
}
}
