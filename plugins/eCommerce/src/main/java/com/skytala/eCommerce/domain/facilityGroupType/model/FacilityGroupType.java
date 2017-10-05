package com.skytala.eCommerce.domain.facilityGroupType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.facilityGroupType.mapper.FacilityGroupTypeMapper;

public class FacilityGroupType implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityGroupTypeId;
private String description;

public String getFacilityGroupTypeId() {
return facilityGroupTypeId;
}

public void setFacilityGroupTypeId(String  facilityGroupTypeId) {
this.facilityGroupTypeId = facilityGroupTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FacilityGroupTypeMapper.map(this);
}
}
