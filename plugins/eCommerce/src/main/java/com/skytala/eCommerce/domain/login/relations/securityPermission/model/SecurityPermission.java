package com.skytala.eCommerce.domain.login.relations.securityPermission.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.securityPermission.mapper.SecurityPermissionMapper;

public class SecurityPermission implements Serializable{

private static final long serialVersionUID = 1L;
private String permissionId;
private String description;

public String getPermissionId() {
return permissionId;
}

public void setPermissionId(String  permissionId) {
this.permissionId = permissionId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SecurityPermissionMapper.map(this);
}
}
