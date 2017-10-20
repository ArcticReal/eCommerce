package com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.carrierAccount.PartyCarrierAccountMapper;

public class PartyCarrierAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String carrierPartyId;
private Timestamp fromDate;
private Timestamp thruDate;
private String accountNumber;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getCarrierPartyId() {
return carrierPartyId;
}

public void setCarrierPartyId(String  carrierPartyId) {
this.carrierPartyId = carrierPartyId;
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

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String  accountNumber) {
this.accountNumber = accountNumber;
}


public Map<String, Object> mapAttributeField() {
return PartyCarrierAccountMapper.map(this);
}
}
