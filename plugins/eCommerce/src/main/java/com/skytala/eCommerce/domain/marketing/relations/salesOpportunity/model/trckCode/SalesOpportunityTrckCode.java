package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.trckCode;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.trckCode.SalesOpportunityTrckCodeMapper;

public class SalesOpportunityTrckCode implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityId;
private String trackingCodeId;
private Timestamp receivedDate;

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
}

public String getTrackingCodeId() {
return trackingCodeId;
}

public void setTrackingCodeId(String  trackingCodeId) {
this.trackingCodeId = trackingCodeId;
}

public Timestamp getReceivedDate() {
return receivedDate;
}

public void setReceivedDate(Timestamp  receivedDate) {
this.receivedDate = receivedDate;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityTrckCodeMapper.map(this);
}
}
