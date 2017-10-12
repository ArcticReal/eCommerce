package com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.mapper.AccommodationClassMapper;

public class AccommodationClass implements Serializable{

private static final long serialVersionUID = 1L;
private String accommodationClassId;
private String parentClassId;
private String description;

public String getAccommodationClassId() {
return accommodationClassId;
}

public void setAccommodationClassId(String  accommodationClassId) {
this.accommodationClassId = accommodationClassId;
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


public Map<String, Object> mapAttributeField() {
return AccommodationClassMapper.map(this);
}
}
