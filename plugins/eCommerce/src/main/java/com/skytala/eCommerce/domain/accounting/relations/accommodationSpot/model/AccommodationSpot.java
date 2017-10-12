package com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.mapper.AccommodationSpotMapper;

public class AccommodationSpot implements Serializable{

private static final long serialVersionUID = 1L;
private String accommodationSpotId;
private String accommodationClassId;
private String fixedAssetId;
private Long numberOfSpaces;
private String description;

public String getAccommodationSpotId() {
return accommodationSpotId;
}

public void setAccommodationSpotId(String  accommodationSpotId) {
this.accommodationSpotId = accommodationSpotId;
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

public Long getNumberOfSpaces() {
return numberOfSpaces;
}

public void setNumberOfSpaces(Long  numberOfSpaces) {
this.numberOfSpaces = numberOfSpaces;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return AccommodationSpotMapper.map(this);
}
}
