package com.skytala.eCommerce.domain.party.relations.roleType.model.attr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.roleType.mapper.attr.RoleTypeAttrMapper;

public class RoleTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String roleTypeId;
private String attrName;
private String description;

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return RoleTypeAttrMapper.map(this);
}
}
