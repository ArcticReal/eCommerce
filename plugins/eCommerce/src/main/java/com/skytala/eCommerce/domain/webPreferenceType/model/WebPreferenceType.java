package com.skytala.eCommerce.domain.webPreferenceType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.webPreferenceType.mapper.WebPreferenceTypeMapper;

public class WebPreferenceType implements Serializable{

private static final long serialVersionUID = 1L;
private String webPreferenceTypeId;
private String description;

public String getWebPreferenceTypeId() {
return webPreferenceTypeId;
}

public void setWebPreferenceTypeId(String  webPreferenceTypeId) {
this.webPreferenceTypeId = webPreferenceTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return WebPreferenceTypeMapper.map(this);
}
}
