package com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemWorkEffort.CustRequestItemWorkEffortMapper;

public class CustRequestItemWorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String custRequestItemSeqId;
private String workEffortId;

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

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestItemWorkEffortMapper.map(this);
}
}
