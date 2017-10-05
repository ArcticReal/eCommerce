package com.skytala.eCommerce.domain.glAccountClass.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.glAccountClass.mapper.GlAccountClassMapper;

public class GlAccountClass implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountClassId;
private String parentClassId;
private String description;
private Boolean isAssetClass;

public String getGlAccountClassId() {
return glAccountClassId;
}

public void setGlAccountClassId(String  glAccountClassId) {
this.glAccountClassId = glAccountClassId;
}

public String getParentClassId() {
return parentClassId;
}

public void setParentClassId(String  parentClassId) {
this.parentClassId = parentClassId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Boolean getIsAssetClass() {
return isAssetClass;
}

public void setIsAssetClass(Boolean  isAssetClass) {
this.isAssetClass = isAssetClass;
}


public Map<String, Object> mapAttributeField() {
return GlAccountClassMapper.map(this);
}
}
