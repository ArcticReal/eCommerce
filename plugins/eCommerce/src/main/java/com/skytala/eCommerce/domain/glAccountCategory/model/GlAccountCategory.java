package com.skytala.eCommerce.domain.glAccountCategory.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.glAccountCategory.mapper.GlAccountCategoryMapper;

public class GlAccountCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountCategoryId;
private String glAccountCategoryTypeId;
private String description;

public String getGlAccountCategoryId() {
return glAccountCategoryId;
}

public void setGlAccountCategoryId(String  glAccountCategoryId) {
this.glAccountCategoryId = glAccountCategoryId;
}

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
return GlAccountCategoryMapper.map(this);
}
}
