package com.skytala.eCommerce.domain.skillType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.skillType.mapper.SkillTypeMapper;

public class SkillType implements Serializable{

private static final long serialVersionUID = 1L;
private String skillTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getSkillTypeId() {
return skillTypeId;
}

public void setSkillTypeId(String  skillTypeId) {
this.skillTypeId = skillTypeId;
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

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SkillTypeMapper.map(this);
}
}
