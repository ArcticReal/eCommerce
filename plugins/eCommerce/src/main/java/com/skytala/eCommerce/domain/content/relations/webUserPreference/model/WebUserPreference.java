package com.skytala.eCommerce.domain.content.relations.webUserPreference.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.mapper.WebUserPreferenceMapper;

public class WebUserPreference implements Serializable{

private static final long serialVersionUID = 1L;
private String userLoginId;
private String partyId;
private String visitId;
private String webPreferenceTypeId;
private String webPreferenceValue;

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public String getWebPreferenceTypeId() {
return webPreferenceTypeId;
}

public void setWebPreferenceTypeId(String  webPreferenceTypeId) {
this.webPreferenceTypeId = webPreferenceTypeId;
}

public String getWebPreferenceValue() {
return webPreferenceValue;
}

public void setWebPreferenceValue(String  webPreferenceValue) {
this.webPreferenceValue = webPreferenceValue;
}


public Map<String, Object> mapAttributeField() {
return WebUserPreferenceMapper.map(this);
}
}
