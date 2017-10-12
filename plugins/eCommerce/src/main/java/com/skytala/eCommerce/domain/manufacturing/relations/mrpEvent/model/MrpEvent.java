package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.MrpEventMapper;

public class MrpEvent implements Serializable{

private static final long serialVersionUID = 1L;
private String mrpId;
private String productId;
private Timestamp eventDate;
private String mrpEventTypeId;
private String facilityId;
private BigDecimal quantity;
private String eventName;
private Boolean isLate;

public String getMrpId() {
return mrpId;
}

public void setMrpId(String  mrpId) {
this.mrpId = mrpId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public Timestamp getEventDate() {
return eventDate;
}

public void setEventDate(Timestamp  eventDate) {
this.eventDate = eventDate;
}

public String getMrpEventTypeId() {
return mrpEventTypeId;
}

public void setMrpEventTypeId(String  mrpEventTypeId) {
this.mrpEventTypeId = mrpEventTypeId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public String getEventName() {
return eventName;
}

public void setEventName(String  eventName) {
this.eventName = eventName;
}

public Boolean getIsLate() {
return isLate;
}

public void setIsLate(Boolean  isLate) {
this.isLate = isLate;
}


public Map<String, Object> mapAttributeField() {
return MrpEventMapper.map(this);
}
}
