package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.mapper.TarpittedLoginViewMapper;

public class TarpittedLoginView implements Serializable{

private static final long serialVersionUID = 1L;
private String viewNameId;
private String userLoginId;
private Long tarpitReleaseDateTime;

public String getViewNameId() {
return viewNameId;
}

public void setViewNameId(String  viewNameId) {
this.viewNameId = viewNameId;
}

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public Long getTarpitReleaseDateTime() {
return tarpitReleaseDateTime;
}

public void setTarpitReleaseDateTime(Long  tarpitReleaseDateTime) {
this.tarpitReleaseDateTime = tarpitReleaseDateTime;
}


public Map<String, Object> mapAttributeField() {
return TarpittedLoginViewMapper.map(this);
}
}
