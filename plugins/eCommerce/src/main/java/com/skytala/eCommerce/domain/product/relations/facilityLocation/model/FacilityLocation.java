package com.skytala.eCommerce.domain.product.relations.facilityLocation.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.mapper.FacilityLocationMapper;

public class FacilityLocation implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityId;
private String locationSeqId;
private String locationTypeEnumId;
private String areaId;
private String aisleId;
private String sectionId;
private String levelId;
private String positionId;
private String geoPointId;

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getLocationSeqId() {
return locationSeqId;
}

public void setLocationSeqId(String  locationSeqId) {
this.locationSeqId = locationSeqId;
}

public String getLocationTypeEnumId() {
return locationTypeEnumId;
}

public void setLocationTypeEnumId(String  locationTypeEnumId) {
this.locationTypeEnumId = locationTypeEnumId;
}

public String getAreaId() {
return areaId;
}

public void setAreaId(String  areaId) {
this.areaId = areaId;
}

public String getAisleId() {
return aisleId;
}

public void setAisleId(String  aisleId) {
this.aisleId = aisleId;
}

public String getSectionId() {
return sectionId;
}

public void setSectionId(String  sectionId) {
this.sectionId = sectionId;
}

public String getLevelId() {
return levelId;
}

public void setLevelId(String  levelId) {
this.levelId = levelId;
}

public String getPositionId() {
return positionId;
}

public void setPositionId(String  positionId) {
this.positionId = positionId;
}

public String getGeoPointId() {
return geoPointId;
}

public void setGeoPointId(String  geoPointId) {
this.geoPointId = geoPointId;
}


public Map<String, Object> mapAttributeField() {
return FacilityLocationMapper.map(this);
}
}
