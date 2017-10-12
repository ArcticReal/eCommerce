package com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.mapper.WorkEffortTransBoxMapper;

public class WorkEffortTransBox implements Serializable{

private static final long serialVersionUID = 1L;
private String processWorkEffortId;
private String toActivityId;
private String transitionId;

public String getProcessWorkEffortId() {
return processWorkEffortId;
}

public void setProcessWorkEffortId(String  processWorkEffortId) {
this.processWorkEffortId = processWorkEffortId;
}

public String getToActivityId() {
return toActivityId;
}

public void setToActivityId(String  toActivityId) {
this.toActivityId = toActivityId;
}

public String getTransitionId() {
return transitionId;
}

public void setTransitionId(String  transitionId) {
this.transitionId = transitionId;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortTransBoxMapper.map(this);
}
}
