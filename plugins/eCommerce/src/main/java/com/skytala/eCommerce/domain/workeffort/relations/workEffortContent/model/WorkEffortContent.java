package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.mapper.WorkEffortContentMapper;

public class WorkEffortContent implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String contentId;
private String workEffortContentTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getWorkEffortContentTypeId() {
return workEffortContentTypeId;
}

public void setWorkEffortContentTypeId(String  workEffortContentTypeId) {
this.workEffortContentTypeId = workEffortContentTypeId;
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
return WorkEffortContentMapper.map(this);
}
}
