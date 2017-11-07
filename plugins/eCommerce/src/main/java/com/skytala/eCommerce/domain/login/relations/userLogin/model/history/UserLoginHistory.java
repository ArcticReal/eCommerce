package com.skytala.eCommerce.domain.login.relations.userLogin.model.history;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.history.UserLoginHistoryMapper;

public class UserLoginHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String userLoginId;
private String visitId;
private Timestamp fromDate;
private Timestamp thruDate;
private String passwordUsed;
private Boolean successfulLogin;

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
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

public String getPasswordUsed() {
return passwordUsed;
}

public void setPasswordUsed(String  passwordUsed) {
this.passwordUsed = passwordUsed;
}

public Boolean getSuccessfulLogin() {
return successfulLogin;
}

public void setSuccessfulLogin(Boolean  successfulLogin) {
this.successfulLogin = successfulLogin;
}


public Map<String, Object> mapAttributeField() {
return UserLoginHistoryMapper.map(this);
}
}
