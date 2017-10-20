package com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.visit;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.visit.TrackingCodeVisitMapper;

public class TrackingCodeVisit implements Serializable{

private static final long serialVersionUID = 1L;
private String trackingCodeId;
private String visitId;
private Timestamp fromDate;
private String sourceEnumId;

public String getTrackingCodeId() {
return trackingCodeId;
}

public void setTrackingCodeId(String  trackingCodeId) {
this.trackingCodeId = trackingCodeId;
}

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public String getSourceEnumId() {
return sourceEnumId;
}

public void setSourceEnumId(String  sourceEnumId) {
this.sourceEnumId = sourceEnumId;
}


public Map<String, Object> mapAttributeField() {
return TrackingCodeVisitMapper.map(this);
}
}
