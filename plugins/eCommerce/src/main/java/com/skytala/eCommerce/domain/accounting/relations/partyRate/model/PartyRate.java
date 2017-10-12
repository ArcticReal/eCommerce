package com.skytala.eCommerce.domain.accounting.relations.partyRate.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.mapper.PartyRateMapper;

public class PartyRate implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String rateTypeId;
private Boolean defaultRate;
private BigDecimal percentageUsed;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRateTypeId() {
return rateTypeId;
}

public void setRateTypeId(String  rateTypeId) {
this.rateTypeId = rateTypeId;
}

public Boolean getDefaultRate() {
return defaultRate;
}

public void setDefaultRate(Boolean  defaultRate) {
this.defaultRate = defaultRate;
}

public BigDecimal getPercentageUsed() {
return percentageUsed;
}

public void setPercentageUsed(BigDecimal  percentageUsed) {
this.percentageUsed = percentageUsed;
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
return PartyRateMapper.map(this);
}
}
