package com.skytala.eCommerce.domain.login.relations.protectedView.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.protectedView.mapper.ProtectedViewMapper;

public class ProtectedView implements Serializable{

private static final long serialVersionUID = 1L;
private String groupId;
private String viewNameId;
private Long maxHits;
private Long maxHitsDuration;
private Long tarpitDuration;

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
}

public String getViewNameId() {
return viewNameId;
}

public void setViewNameId(String  viewNameId) {
this.viewNameId = viewNameId;
}

public Long getMaxHits() {
return maxHits;
}

public void setMaxHits(Long  maxHits) {
this.maxHits = maxHits;
}

public Long getMaxHitsDuration() {
return maxHitsDuration;
}

public void setMaxHitsDuration(Long  maxHitsDuration) {
this.maxHitsDuration = maxHitsDuration;
}

public Long getTarpitDuration() {
return tarpitDuration;
}

public void setTarpitDuration(Long  tarpitDuration) {
this.tarpitDuration = tarpitDuration;
}


public Map<String, Object> mapAttributeField() {
return ProtectedViewMapper.map(this);
}
}
