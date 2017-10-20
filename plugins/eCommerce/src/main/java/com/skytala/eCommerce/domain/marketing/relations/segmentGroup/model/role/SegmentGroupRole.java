package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.role.SegmentGroupRoleMapper;

public class SegmentGroupRole implements Serializable{

private static final long serialVersionUID = 1L;
private String segmentGroupId;
private String partyId;
private String roleTypeId;

public String getSegmentGroupId() {
return segmentGroupId;
}

public void setSegmentGroupId(String  segmentGroupId) {
this.segmentGroupId = segmentGroupId;
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


public Map<String, Object> mapAttributeField() {
return SegmentGroupRoleMapper.map(this);
}
}
