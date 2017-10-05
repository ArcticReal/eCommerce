package com.skytala.eCommerce.domain.reorderGuideline.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.reorderGuideline.mapper.ReorderGuidelineMapper;

public class ReorderGuideline implements Serializable{

private static final long serialVersionUID = 1L;
private String reorderGuidelineId;
private String productId;
private String partyId;
private String roleTypeId;
private String facilityId;
private String geoId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal reorderQuantity;
private BigDecimal reorderLevel;

public String getReorderGuidelineId() {
return reorderGuidelineId;
}

public void setReorderGuidelineId(String  reorderGuidelineId) {
this.reorderGuidelineId = reorderGuidelineId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
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

public BigDecimal getReorderQuantity() {
return reorderQuantity;
}

public void setReorderQuantity(BigDecimal  reorderQuantity) {
this.reorderQuantity = reorderQuantity;
}

public BigDecimal getReorderLevel() {
return reorderLevel;
}

public void setReorderLevel(BigDecimal  reorderLevel) {
this.reorderLevel = reorderLevel;
}


public Map<String, Object> mapAttributeField() {
return ReorderGuidelineMapper.map(this);
}
}
