package com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityGroup.UserLoginSecurityGroupMapper;

public class UserLoginSecurityGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String userLoginId;
private String groupId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
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


public Map<String, Object> mapAttributeField() {
return UserLoginSecurityGroupMapper.map(this);
}
}
