package com.skytala.eCommerce.domain.custRequestStatus.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.custRequestStatus.mapper.CustRequestStatusMapper;

public class CustRequestStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestStatusId;
private String statusId;
private String custRequestId;
private String custRequestItemSeqId;
private Timestamp statusDate;
private String changeByUserLoginId;

public String getCustRequestStatusId() {
return custRequestStatusId;
}

public void setCustRequestStatusId(String  custRequestStatusId) {
this.custRequestStatusId = custRequestStatusId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

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

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestStatusMapper.map(this);
}
}
