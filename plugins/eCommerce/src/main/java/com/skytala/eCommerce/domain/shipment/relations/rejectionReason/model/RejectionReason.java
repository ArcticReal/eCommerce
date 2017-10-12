package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.mapper.RejectionReasonMapper;

public class RejectionReason implements Serializable{

private static final long serialVersionUID = 1L;
private String rejectionId;
private String description;

public String getRejectionId() {
return rejectionId;
}

public void setRejectionId(String  rejectionId) {
this.rejectionId = rejectionId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return RejectionReasonMapper.map(this);
}
}
