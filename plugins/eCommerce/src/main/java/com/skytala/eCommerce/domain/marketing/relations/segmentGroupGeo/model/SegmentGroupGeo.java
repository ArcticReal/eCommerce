package com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.mapper.SegmentGroupGeoMapper;

public class SegmentGroupGeo implements Serializable{

private static final long serialVersionUID = 1L;
private String segmentGroupId;
private String geoId;

public String getSegmentGroupId() {
return segmentGroupId;
}

public void setSegmentGroupId(String  segmentGroupId) {
this.segmentGroupId = segmentGroupId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
}


public Map<String, Object> mapAttributeField() {
return SegmentGroupGeoMapper.map(this);
}
}
