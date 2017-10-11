package com.skytala.eCommerce.domain.order.relations.returnStatus.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.returnStatus.mapper.ReturnStatusMapper;

public class ReturnStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String returnStatusId;
private String statusId;
private String returnId;
private String returnItemSeqId;
private String changeByUserLoginId;
private Timestamp statusDatetime;

public String getReturnStatusId() {
return returnStatusId;
}

public void setReturnStatusId(String  returnStatusId) {
this.returnStatusId = returnStatusId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getReturnId() {
return returnId;
}

public void setReturnId(String  returnId) {
this.returnId = returnId;
}

public String getReturnItemSeqId() {
return returnItemSeqId;
}

public void setReturnItemSeqId(String  returnItemSeqId) {
this.returnItemSeqId = returnItemSeqId;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}

public Timestamp getStatusDatetime() {
return statusDatetime;
}

public void setStatusDatetime(Timestamp  statusDatetime) {
this.statusDatetime = statusDatetime;
}


public Map<String, Object> mapAttributeField() {
return ReturnStatusMapper.map(this);
}
}
