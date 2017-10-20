package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.status.WorkEffortStatusMapper;

public class WorkEffortStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String statusId;
private Timestamp statusDatetime;
private String setByUserLogin;
private String reason;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getStatusDatetime() {
return statusDatetime;
}

public void setStatusDatetime(Timestamp  statusDatetime) {
this.statusDatetime = statusDatetime;
}

public String getSetByUserLogin() {
return setByUserLogin;
}

public void setSetByUserLogin(String  setByUserLogin) {
this.setByUserLogin = setByUserLogin;
}

public String getReason() {
return reason;
}

public void setReason(String  reason) {
this.reason = reason;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortStatusMapper.map(this);
}
}
