package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.mapper.AccommodationMapMapper;

public class AccommodationMap implements Serializable{

private static final long serialVersionUID = 1L;
private String accommodationMapId;
private String accommodationClassId;
private String fixedAssetId;
private String accommodationMapTypeId;
private Long numberOfSpaces;

public String getAccommodationMapId() {
return accommodationMapId;
}

public void setAccommodationMapId(String  accommodationMapId) {
this.accommodationMapId = accommodationMapId;
}

public String getAccommodationClassId() {
return accommodationClassId;
}

public void setAccommodationClassId(String  accommodationClassId) {
this.accommodationClassId = accommodationClassId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getAccommodationMapTypeId() {
return accommodationMapTypeId;
}

public void setAccommodationMapTypeId(String  accommodationMapTypeId) {
this.accommodationMapTypeId = accommodationMapTypeId;
}

public Long getNumberOfSpaces() {
return numberOfSpaces;
}

public void setNumberOfSpaces(Long  numberOfSpaces) {
this.numberOfSpaces = numberOfSpaces;
}


public Map<String, Object> mapAttributeField() {
return AccommodationMapMapper.map(this);
}
}
