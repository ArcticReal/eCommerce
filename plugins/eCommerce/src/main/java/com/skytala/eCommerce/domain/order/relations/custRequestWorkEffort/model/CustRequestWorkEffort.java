package com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.mapper.CustRequestWorkEffortMapper;

public class CustRequestWorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String workEffortId;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestWorkEffortMapper.map(this);
}
}
