package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.mapper.FixedAssetStdCostMapper;

public class FixedAssetStdCost implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String fixedAssetStdCostTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String amountUomId;
private BigDecimal amount;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getFixedAssetStdCostTypeId() {
return fixedAssetStdCostTypeId;
}

public void setFixedAssetStdCostTypeId(String  fixedAssetStdCostTypeId) {
this.fixedAssetStdCostTypeId = fixedAssetStdCostTypeId;
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

public String getAmountUomId() {
return amountUomId;
}

public void setAmountUomId(String  amountUomId) {
this.amountUomId = amountUomId;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetStdCostMapper.map(this);
}
}
