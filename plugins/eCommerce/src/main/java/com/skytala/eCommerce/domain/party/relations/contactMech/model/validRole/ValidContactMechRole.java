package com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.validRole.ValidContactMechRoleMapper;

public class ValidContactMechRole implements Serializable{

private static final long serialVersionUID = 1L;
private String roleTypeId;
private String contactMechTypeId;

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}


public Map<String, Object> mapAttributeField() {
return ValidContactMechRoleMapper.map(this);
}
}
