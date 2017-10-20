package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.workEffort;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.workEffort.SalesOpportunityWorkEffortMapper;

public class SalesOpportunityWorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityId;
private String workEffortId;

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityWorkEffortMapper.map(this);
}
}
