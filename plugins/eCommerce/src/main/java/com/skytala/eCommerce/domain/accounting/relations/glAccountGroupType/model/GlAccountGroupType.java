package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupType.mapper.GlAccountGroupTypeMapper;

public class GlAccountGroupType implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountGroupTypeId;
private String description;

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
return GlAccountGroupTypeMapper.map(this);
}
}
