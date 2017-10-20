package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.mapper.type.AccommodationMapTypeMapper;

public class AccommodationMapType implements Serializable{

private static final long serialVersionUID = 1L;
private String accommodationMapTypeId;
private String description;

public String getAccommodationMapTypeId() {
return accommodationMapTypeId;
}

public void setAccommodationMapTypeId(String  accommodationMapTypeId) {
this.accommodationMapTypeId = accommodationMapTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return AccommodationMapTypeMapper.map(this);
}
}
