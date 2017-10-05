package com.skytala.eCommerce.domain.agreement.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.agreement.mapper.AgreementMapper;

public class Agreement implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String productId;
private String partyIdFrom;
private String partyIdTo;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private String agreementTypeId;
private Timestamp agreementDate;
private Timestamp fromDate;
private Timestamp thruDate;
private String description;
private String textData;

public String getAgreementId() {
return agreementId;
}

public void setAgreementId(String  agreementId) {
this.agreementId = agreementId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}

public String getRoleTypeIdFrom() {
return roleTypeIdFrom;
}

public void setRoleTypeIdFrom(String  roleTypeIdFrom) {
this.roleTypeIdFrom = roleTypeIdFrom;
}

public String getRoleTypeIdTo() {
return roleTypeIdTo;
}

public void setRoleTypeIdTo(String  roleTypeIdTo) {
this.roleTypeIdTo = roleTypeIdTo;
}

public String getAgreementTypeId() {
return agreementTypeId;
}

public void setAgreementTypeId(String  agreementTypeId) {
this.agreementTypeId = agreementTypeId;
}

public Timestamp getAgreementDate() {
return agreementDate;
}

public void setAgreementDate(Timestamp  agreementDate) {
this.agreementDate = agreementDate;
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

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getTextData() {
return textData;
}

public void setTextData(String  textData) {
this.textData = textData;
}


public Map<String, Object> mapAttributeField() {
return AgreementMapper.map(this);
}
}
