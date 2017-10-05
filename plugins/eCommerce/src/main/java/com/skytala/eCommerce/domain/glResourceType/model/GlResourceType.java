package com.skytala.eCommerce.domain.glResourceType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.glResourceType.mapper.GlResourceTypeMapper;

public class GlResourceType implements Serializable{

private static final long serialVersionUID = 1L;
private String glResourceTypeId;
private String description;

public String getGlResourceTypeId() {
return glResourceTypeId;
}

public void setGlResourceTypeId(String  glResourceTypeId) {
this.glResourceTypeId = glResourceTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return GlResourceTypeMapper.map(this);
}
}
