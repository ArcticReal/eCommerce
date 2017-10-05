package com.skytala.eCommerce.domain.terminationType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.terminationType.mapper.TerminationTypeMapper;

public class TerminationType implements Serializable{

private static final long serialVersionUID = 1L;
private String terminationTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getTerminationTypeId() {
return terminationTypeId;
}

public void setTerminationTypeId(String  terminationTypeId) {
this.terminationTypeId = terminationTypeId;
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
return TerminationTypeMapper.map(this);
}
}
