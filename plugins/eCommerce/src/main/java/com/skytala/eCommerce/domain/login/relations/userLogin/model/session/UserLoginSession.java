package com.skytala.eCommerce.domain.login.relations.userLogin.model.session;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.session.UserLoginSessionMapper;

public class UserLoginSession implements Serializable{

private static final long serialVersionUID = 1L;
private String userLoginId;
private Timestamp savedDate;
private String sessionData;

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public Timestamp getSavedDate() {
return savedDate;
}

public void setSavedDate(Timestamp  savedDate) {
this.savedDate = savedDate;
}

public String getSessionData() {
return sessionData;
}

public void setSessionData(String  sessionData) {
this.sessionData = sessionData;
}


public Map<String, Object> mapAttributeField() {
return UserLoginSessionMapper.map(this);
}
}
