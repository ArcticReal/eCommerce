package com.skytala.eCommerce.domain.accounting.relations.rateAmount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.mapper.RateAmountMapper;

public class RateAmount implements Serializable{

private static final long serialVersionUID = 1L;
private String rateTypeId;
private String rateCurrencyUomId;
private String periodTypeId;
private String workEffortId;
private String partyId;
private String emplPositionTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal rateAmount;

public String getRateTypeId() {
return rateTypeId;
}

public void setRateTypeId(String  rateTypeId) {
this.rateTypeId = rateTypeId;
}

public String getRateCurrencyUomId() {
return rateCurrencyUomId;
}

public void setRateCurrencyUomId(String  rateCurrencyUomId) {
this.rateCurrencyUomId = rateCurrencyUomId;
}

public String getPeriodTypeId() {
return periodTypeId;
}

public void setPeriodTypeId(String  periodTypeId) {
this.periodTypeId = periodTypeId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getEmplPositionTypeId() {
return emplPositionTypeId;
}

public void setEmplPositionTypeId(String  emplPositionTypeId) {
this.emplPositionTypeId = emplPositionTypeId;
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

public BigDecimal getRateAmount() {
return rateAmount;
}

public void setRateAmount(BigDecimal  rateAmount) {
this.rateAmount = rateAmount;
}


public Map<String, Object> mapAttributeField() {
return RateAmountMapper.map(this);
}
}
