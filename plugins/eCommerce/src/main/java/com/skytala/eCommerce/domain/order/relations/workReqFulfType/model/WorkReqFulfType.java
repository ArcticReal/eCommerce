package com.skytala.eCommerce.domain.order.relations.workReqFulfType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.mapper.WorkReqFulfTypeMapper;

public class WorkReqFulfType implements Serializable{

private static final long serialVersionUID = 1L;
private String workReqFulfTypeId;
private String description;

public String getWorkReqFulfTypeId() {
return workReqFulfTypeId;
}

public void setWorkReqFulfTypeId(String  workReqFulfTypeId) {
this.workReqFulfTypeId = workReqFulfTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return WorkReqFulfTypeMapper.map(this);
}
}
