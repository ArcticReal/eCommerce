package com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.passwordHistory.UserLoginPasswordHistoryMapper;

public class UserLoginPasswordHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String userLoginId;
private Timestamp fromDate;
private Timestamp thruDate;
private String currentPassword;

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
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

public String getCurrentPassword() {
return currentPassword;
}

public void setCurrentPassword(String  currentPassword) {
this.currentPassword = currentPassword;
}


public Map<String, Object> mapAttributeField() {
return UserLoginPasswordHistoryMapper.map(this);
}
}
