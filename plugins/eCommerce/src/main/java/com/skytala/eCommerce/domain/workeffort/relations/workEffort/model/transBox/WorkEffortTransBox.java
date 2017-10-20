package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.transBox.WorkEffortTransBoxMapper;

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
