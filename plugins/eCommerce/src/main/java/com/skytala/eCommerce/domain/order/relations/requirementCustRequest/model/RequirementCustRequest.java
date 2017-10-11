package com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.mapper.RequirementCustRequestMapper;

public class RequirementCustRequest implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String custRequestItemSeqId;
private String requirementId;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getCustRequestItemSeqId() {
return custRequestItemSeqId;
}

public void setCustRequestItemSeqId(String  custRequestItemSeqId) {
this.custRequestItemSeqId = custRequestItemSeqId;
}

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}


public Map<String, Object> mapAttributeField() {
return RequirementCustRequestMapper.map(this);
}
}
