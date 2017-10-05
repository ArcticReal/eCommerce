package com.skytala.eCommerce.domain.partyNeed.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.partyNeed.mapper.PartyNeedMapper;

public class PartyNeed implements Serializable{

private static final long serialVersionUID = 1L;
private String partyNeedId;
private String partyId;
private String roleTypeId;
private String partyTypeId;
private String needTypeId;
private String communicationEventId;
private String productId;
private String productCategoryId;
private String visitId;
private Timestamp datetimeRecorded;
private String description;

public String getPartyNeedId() {
return partyNeedId;
}

public void setPartyNeedId(String  partyNeedId) {
this.partyNeedId = partyNeedId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getPartyTypeId() {
return partyTypeId;
}

public void setPartyTypeId(String  partyTypeId) {
this.partyTypeId = partyTypeId;
}

public String getNeedTypeId() {
return needTypeId;
}

public void setNeedTypeId(String  needTypeId) {
this.needTypeId = needTypeId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public Timestamp getDatetimeRecorded() {
return datetimeRecorded;
}

public void setDatetimeRecorded(Timestamp  datetimeRecorded) {
this.datetimeRecorded = datetimeRecorded;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return PartyNeedMapper.map(this);
}
}
