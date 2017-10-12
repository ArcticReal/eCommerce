package com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.mapper.FinAccountAuthMapper;

public class FinAccountAuth implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountAuthId;
private String finAccountId;
private BigDecimal amount;
private String currencyUomId;
private Timestamp authorizationDate;
private Timestamp fromDate;
private Timestamp thruDate;

public String getFinAccountAuthId() {
return finAccountAuthId;
}

public void setFinAccountAuthId(String  finAccountAuthId) {
this.finAccountAuthId = finAccountAuthId;
}

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public Timestamp getAuthorizationDate() {
return authorizationDate;
}

public void setAuthorizationDate(Timestamp  authorizationDate) {
this.authorizationDate = authorizationDate;
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
return FinAccountAuthMapper.map(this);
}
}
