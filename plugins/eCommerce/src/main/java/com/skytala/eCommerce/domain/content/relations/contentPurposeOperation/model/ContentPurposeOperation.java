package com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.mapper.ContentPurposeOperationMapper;

public class ContentPurposeOperation implements Serializable{

private static final long serialVersionUID = 1L;
private String contentPurposeTypeId;
private String contentOperationId;
private String roleTypeId;
private String statusId;
private String privilegeEnumId;

public String getContentPurposeTypeId() {
return contentPurposeTypeId;
}

public void setContentPurposeTypeId(String  contentPurposeTypeId) {
this.contentPurposeTypeId = contentPurposeTypeId;
}

public String getContentOperationId() {
return contentOperationId;
}

public void setContentOperationId(String  contentOperationId) {
this.contentOperationId = contentOperationId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getPrivilegeEnumId() {
return privilegeEnumId;
}

public void setPrivilegeEnumId(String  privilegeEnumId) {
this.privilegeEnumId = privilegeEnumId;
}


public Map<String, Object> mapAttributeField() {
return ContentPurposeOperationMapper.map(this);
}
}
