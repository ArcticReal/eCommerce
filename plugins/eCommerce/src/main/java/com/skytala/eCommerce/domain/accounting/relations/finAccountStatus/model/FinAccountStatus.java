package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.mapper.FinAccountStatusMapper;

public class FinAccountStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountId;
private String statusId;
private Timestamp statusDate;
private Timestamp statusEndDate;
private String changeByUserLoginId;

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public Timestamp getStatusEndDate() {
return statusEndDate;
}

public void setStatusEndDate(Timestamp  statusEndDate) {
this.statusEndDate = statusEndDate;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return FinAccountStatusMapper.map(this);
}
}
