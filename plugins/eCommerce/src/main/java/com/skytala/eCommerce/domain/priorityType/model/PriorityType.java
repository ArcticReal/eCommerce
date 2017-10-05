package com.skytala.eCommerce.domain.priorityType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.priorityType.mapper.PriorityTypeMapper;

public class PriorityType implements Serializable{

private static final long serialVersionUID = 1L;
private String priorityTypeId;
private String description;

public String getPriorityTypeId() {
return priorityTypeId;
}

public void setPriorityTypeId(String  priorityTypeId) {
this.priorityTypeId = priorityTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PriorityTypeMapper.map(this);
}
}
