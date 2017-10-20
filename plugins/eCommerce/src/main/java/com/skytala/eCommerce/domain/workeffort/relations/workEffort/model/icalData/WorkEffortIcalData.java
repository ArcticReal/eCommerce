package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.icalData;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.icalData.WorkEffortIcalDataMapper;

public class WorkEffortIcalData implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String icalData;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getIcalData() {
return icalData;
}

public void setIcalData(String  icalData) {
this.icalData = icalData;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortIcalDataMapper.map(this);
}
}
