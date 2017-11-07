package com.skytala.eCommerce.domain.login.relations.securityGroup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.SecurityGroupMapper;

public class SecurityGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String groupId;
private String description;

public String getGroupId() {
return groupId;
}

public void setGroupId(String  groupId) {
this.groupId = groupId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SecurityGroupMapper.map(this);
}
}
