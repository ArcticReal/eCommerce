package com.skytala.eCommerce.domain.glXbrlClass.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.glXbrlClass.mapper.GlXbrlClassMapper;

public class GlXbrlClass implements Serializable{

private static final long serialVersionUID = 1L;
private String glXbrlClassId;
private String description;

public String getGlXbrlClassId() {
return glXbrlClassId;
}

public void setGlXbrlClassId(String  glXbrlClassId) {
this.glXbrlClassId = glXbrlClassId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return GlXbrlClassMapper.map(this);
}
}
