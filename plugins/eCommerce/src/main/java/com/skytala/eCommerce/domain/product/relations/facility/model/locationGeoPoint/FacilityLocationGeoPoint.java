package com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.locationGeoPoint.FacilityLocationGeoPointMapper;

public class FacilityLocationGeoPoint implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityId;
private String locationSeqId;
private String geoPointId;
private Timestamp fromDate;
private Timestamp thruDate;

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

public String getGeoPointId() {
return geoPointId;
}

public void setGeoPointId(String  geoPointId) {
this.geoPointId = geoPointId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return FacilityLocationGeoPointMapper.map(this);
}
}
