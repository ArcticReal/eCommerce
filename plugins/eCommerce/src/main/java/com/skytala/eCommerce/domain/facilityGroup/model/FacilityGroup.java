package com.skytala.eCommerce.domain.facilityGroup.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.facilityGroup.mapper.FacilityGroupMapper;

public class FacilityGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityGroupId;
private String facilityGroupTypeId;
private String primaryParentGroupId;
private String facilityGroupName;
private String description;

public String getFacilityGroupId() {
return facilityGroupId;
}

public void setFacilityGroupId(String  facilityGroupId) {
this.facilityGroupId = facilityGroupId;
}

public String getFacilityGroupTypeId() {
return facilityGroupTypeId;
}

public void setFacilityGroupTypeId(String  facilityGroupTypeId) {
this.facilityGroupTypeId = facilityGroupTypeId;
}

public String getPrimaryParentGroupId() {
return primaryParentGroupId;
}

public void setPrimaryParentGroupId(String  primaryParentGroupId) {
this.primaryParentGroupId = primaryParentGroupId;
}

public String getFacilityGroupName() {
return facilityGroupName;
}

public void setFacilityGroupName(String  facilityGroupName) {
this.facilityGroupName = facilityGroupName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FacilityGroupMapper.map(this);
}
}
