package com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.permission.SecurityGroupPermissionMapper;

public class SecurityGroupPermission implements Serializable{

private static final long serialVersionUID = 1L;
private String groupId;
private String permissionId;

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
}

public String getPermissionId() {
return permissionId;
}

public void setPermissionId(String  permissionId) {
this.permissionId = permissionId;
}


public Map<String, Object> mapAttributeField() {
return SecurityGroupPermissionMapper.map(this);
}
}
