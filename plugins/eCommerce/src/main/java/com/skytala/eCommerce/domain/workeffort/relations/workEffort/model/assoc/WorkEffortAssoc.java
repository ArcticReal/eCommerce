package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assoc;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assoc.WorkEffortAssocMapper;

public class WorkEffortAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortIdFrom;
private String workEffortIdTo;
private String workEffortAssocTypeId;
private Long sequenceNum;
private Timestamp fromDate;
private Timestamp thruDate;

public String getWorkEffortIdFrom() {
return workEffortIdFrom;
}

public void setWorkEffortIdFrom(String  workEffortIdFrom) {
this.workEffortIdFrom = workEffortIdFrom;
}

public String getWorkEffortIdTo() {
return workEffortIdTo;
}

public void setWorkEffortIdTo(String  workEffortIdTo) {
this.workEffortIdTo = workEffortIdTo;
}

public String getWorkEffortAssocTypeId() {
return workEffortAssocTypeId;
}

public void setWorkEffortAssocTypeId(String  workEffortAssocTypeId) {
this.workEffortAssocTypeId = workEffortAssocTypeId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortAssocMapper.map(this);
}
}
