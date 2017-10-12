package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.mapper.GlAccountCategoryMemberMapper;

public class GlAccountCategoryMember implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountId;
private String glAccountCategoryId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal amountPercentage;

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getGlAccountCategoryId() {
return glAccountCategoryId;
}

public void setGlAccountCategoryId(String  glAccountCategoryId) {
this.glAccountCategoryId = glAccountCategoryId;
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

public BigDecimal getAmountPercentage() {
return amountPercentage;
}

public void setAmountPercentage(BigDecimal  amountPercentage) {
this.amountPercentage = amountPercentage;
}


public Map<String, Object> mapAttributeField() {
return GlAccountCategoryMemberMapper.map(this);
}
}
