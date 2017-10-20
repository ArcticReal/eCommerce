package com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.categoryType.GlAccountCategoryTypeMapper;

public class GlAccountCategoryType implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountCategoryTypeId;
private String description;

public String getGlAccountCategoryTypeId() {
return glAccountCategoryTypeId;
}

public void setGlAccountCategoryTypeId(String  glAccountCategoryTypeId) {
this.glAccountCategoryTypeId = glAccountCategoryTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return GlAccountCategoryTypeMapper.map(this);
}
}
