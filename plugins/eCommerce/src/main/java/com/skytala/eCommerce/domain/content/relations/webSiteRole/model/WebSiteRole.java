package com.skytala.eCommerce.domain.content.relations.webSiteRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.mapper.WebSiteRoleMapper;

public class WebSiteRole implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String roleTypeId;
private String webSiteId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

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

public String getWebSiteId() {
return webSiteId;
}

public void setWebSiteId(String  webSiteId) {
this.webSiteId = webSiteId;
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
return WebSiteRoleMapper.map(this);
}
}
