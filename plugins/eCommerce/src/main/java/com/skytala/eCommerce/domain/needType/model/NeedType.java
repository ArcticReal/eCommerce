package com.skytala.eCommerce.domain.needType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.needType.mapper.NeedTypeMapper;

public class NeedType implements Serializable{

private static final long serialVersionUID = 1L;
private String needTypeId;
private String description;

public String getNeedTypeId() {
return needTypeId;
}

public void setNeedTypeId(String  needTypeId) {
this.needTypeId = needTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return NeedTypeMapper.map(this);
}
}
