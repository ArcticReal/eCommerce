package com.skytala.eCommerce.domain.custRequestType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.custRequestType.mapper.CustRequestTypeMapper;

public class CustRequestType implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;
private String partyId;

public String getCustRequestTypeId() {
return custRequestTypeId;
}

public void setCustRequestTypeId(String  custRequestTypeId) {
this.custRequestTypeId = custRequestTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestTypeMapper.map(this);
}
}
