package com.skytala.eCommerce.domain.partyRelationshipType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.partyRelationshipType.mapper.PartyRelationshipTypeMapper;

public class PartyRelationshipType implements Serializable{

private static final long serialVersionUID = 1L;
private String partyRelationshipTypeId;
private String parentTypeId;
private Boolean hasTable;
private String partyRelationshipName;
private String description;
private String roleTypeIdValidFrom;
private String roleTypeIdValidTo;

public String getPartyRelationshipTypeId() {
return partyRelationshipTypeId;
}

public void setPartyRelationshipTypeId(String  partyRelationshipTypeId) {
this.partyRelationshipTypeId = partyRelationshipTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getPartyRelationshipName() {
return partyRelationshipName;
}

public void setPartyRelationshipName(String  partyRelationshipName) {
this.partyRelationshipName = partyRelationshipName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getRoleTypeIdValidFrom() {
return roleTypeIdValidFrom;
}

public void setRoleTypeIdValidFrom(String  roleTypeIdValidFrom) {
this.roleTypeIdValidFrom = roleTypeIdValidFrom;
}

public String getRoleTypeIdValidTo() {
return roleTypeIdValidTo;
}

public void setRoleTypeIdValidTo(String  roleTypeIdValidTo) {
this.roleTypeIdValidTo = roleTypeIdValidTo;
}


public Map<String, Object> mapAttributeField() {
return PartyRelationshipTypeMapper.map(this);
}
}
