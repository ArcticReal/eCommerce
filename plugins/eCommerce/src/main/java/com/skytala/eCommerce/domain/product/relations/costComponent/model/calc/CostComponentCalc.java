package com.skytala.eCommerce.domain.product.relations.costComponent.model.calc;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.calc.CostComponentCalcMapper;

public class CostComponentCalc implements Serializable{

private static final long serialVersionUID = 1L;
private String costComponentCalcId;
private String description;
private String costGlAccountTypeId;
private String offsettingGlAccountTypeId;
private BigDecimal fixedCost;
private BigDecimal variableCost;
private Long perMilliSecond;
private String currencyUomId;
private String costCustomMethodId;

public String getCostComponentCalcId() {
return costComponentCalcId;
}

public void setCostComponentCalcId(String  costComponentCalcId) {
this.costComponentCalcId = costComponentCalcId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getCostGlAccountTypeId() {
return costGlAccountTypeId;
}

public void setCostGlAccountTypeId(String  costGlAccountTypeId) {
this.costGlAccountTypeId = costGlAccountTypeId;
}

public String getOffsettingGlAccountTypeId() {
return offsettingGlAccountTypeId;
}

public void setOffsettingGlAccountTypeId(String  offsettingGlAccountTypeId) {
this.offsettingGlAccountTypeId = offsettingGlAccountTypeId;
}

public BigDecimal getFixedCost() {
return fixedCost;
}

public void setFixedCost(BigDecimal  fixedCost) {
this.fixedCost = fixedCost;
}

public BigDecimal getVariableCost() {
return variableCost;
}

public void setVariableCost(BigDecimal  variableCost) {
this.variableCost = variableCost;
}

public Long getPerMilliSecond() {
return perMilliSecond;
}

public void setPerMilliSecond(Long  perMilliSecond) {
this.perMilliSecond = perMilliSecond;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getCostCustomMethodId() {
return costCustomMethodId;
}

public void setCostCustomMethodId(String  costCustomMethodId) {
this.costCustomMethodId = costCustomMethodId;
}


public Map<String, Object> mapAttributeField() {
return CostComponentCalcMapper.map(this);
}
}
