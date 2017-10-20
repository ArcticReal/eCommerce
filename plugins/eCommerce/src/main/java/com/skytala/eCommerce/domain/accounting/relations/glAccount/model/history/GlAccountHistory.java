package com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.history.GlAccountHistoryMapper;

public class GlAccountHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountId;
private String organizationPartyId;
private String customTimePeriodId;
private BigDecimal openingBalance;
private BigDecimal postedDebits;
private BigDecimal postedCredits;
private BigDecimal endingBalance;

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getCustomTimePeriodId() {
return customTimePeriodId;
}

public void setCustomTimePeriodId(String  customTimePeriodId) {
this.customTimePeriodId = customTimePeriodId;
}

public BigDecimal getOpeningBalance() {
return openingBalance;
}

public void setOpeningBalance(BigDecimal  openingBalance) {
this.openingBalance = openingBalance;
}

public BigDecimal getPostedDebits() {
return postedDebits;
}

public void setPostedDebits(BigDecimal  postedDebits) {
this.postedDebits = postedDebits;
}

public BigDecimal getPostedCredits() {
return postedCredits;
}

public void setPostedCredits(BigDecimal  postedCredits) {
this.postedCredits = postedCredits;
}

public BigDecimal getEndingBalance() {
return endingBalance;
}

public void setEndingBalance(BigDecimal  endingBalance) {
this.endingBalance = endingBalance;
}


public Map<String, Object> mapAttributeField() {
return GlAccountHistoryMapper.map(this);
}
}
