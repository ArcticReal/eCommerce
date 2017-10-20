package com.skytala.eCommerce.domain.humanres.relations.employment.model.appSourceType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.appSourceType.EmploymentAppSourceTypeMapper;

public class EmploymentAppSourceType implements Serializable{

private static final long serialVersionUID = 1L;
private String employmentAppSourceTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getEmploymentAppSourceTypeId() {
return employmentAppSourceTypeId;
}

public void setEmploymentAppSourceTypeId(String  employmentAppSourceTypeId) {
this.employmentAppSourceTypeId = employmentAppSourceTypeId;
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
return EmploymentAppSourceTypeMapper.map(this);
}
}
