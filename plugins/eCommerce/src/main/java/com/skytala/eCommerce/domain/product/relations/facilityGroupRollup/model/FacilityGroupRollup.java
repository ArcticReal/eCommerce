package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.mapper.FacilityGroupRollupMapper;

public class FacilityGroupRollup implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityGroupId;
private String parentFacilityGroupId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getFacilityGroupId() {
return facilityGroupId;
}

public void setFacilityGroupId(String  facilityGroupId) {
this.facilityGroupId = facilityGroupId;
}

public String getParentFacilityGroupId() {
return parentFacilityGroupId;
}

public void setParentFacilityGroupId(String  parentFacilityGroupId) {
this.parentFacilityGroupId = parentFacilityGroupId;
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

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return FacilityGroupRollupMapper.map(this);
}
}
