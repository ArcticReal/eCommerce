package com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.mapper.WorkEffortDeliverableProdMapper;

public class WorkEffortDeliverableProd implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String deliverableId;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getDeliverableId() {
return deliverableId;
}

public void setDeliverableId(String  deliverableId) {
this.deliverableId = deliverableId;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortDeliverableProdMapper.map(this);
}
}
