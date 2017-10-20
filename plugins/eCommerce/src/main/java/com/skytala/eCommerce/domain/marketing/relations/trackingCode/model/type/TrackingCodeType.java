package com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.type.TrackingCodeTypeMapper;

public class TrackingCodeType implements Serializable{

private static final long serialVersionUID = 1L;
private String trackingCodeTypeId;
private String description;

public String getTrackingCodeTypeId() {
return trackingCodeTypeId;
}

public void setTrackingCodeTypeId(String  trackingCodeTypeId) {
this.trackingCodeTypeId = trackingCodeTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TrackingCodeTypeMapper.map(this);
}
}
