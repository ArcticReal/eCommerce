package com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.groupMember.GlAccountGroupMemberMapper;

public class GlAccountGroupMember implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountId;
private String glAccountGroupTypeId;
private String glAccountGroupId;

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getGlAccountGroupTypeId() {
return glAccountGroupTypeId;
}

public void setGlAccountGroupTypeId(String  glAccountGroupTypeId) {
this.glAccountGroupTypeId = glAccountGroupTypeId;
}

public String getGlAccountGroupId() {
return glAccountGroupId;
}

public void setGlAccountGroupId(String  glAccountGroupId) {
this.glAccountGroupId = glAccountGroupId;
}


public Map<String, Object> mapAttributeField() {
return GlAccountGroupMemberMapper.map(this);
}
}
