package com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.mapper.GlAccountGroupMapper;

public class GlAccountGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountGroupId;
private String glAccountGroupTypeId;
private String description;

public String getGlAccountGroupId() {
return glAccountGroupId;
}

public void setGlAccountGroupId(String  glAccountGroupId) {
this.glAccountGroupId = glAccountGroupId;
}

public String getGlAccountGroupTypeId() {
return glAccountGroupTypeId;
}

public void setGlAccountGroupTypeId(String  glAccountGroupTypeId) {
this.glAccountGroupTypeId = glAccountGroupTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return GlAccountGroupMapper.map(this);
}
}
