package com.skytala.eCommerce.domain.billingAccountTerm.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.billingAccountTerm.mapper.BillingAccountTermMapper;

public class BillingAccountTerm implements Serializable{

private static final long serialVersionUID = 1L;
private String billingAccountTermId;
private String billingAccountId;
private String termTypeId;
private BigDecimal termValue;
private Long termDays;
private String uomId;

public String getBillingAccountTermId() {
return billingAccountTermId;
}

public void setBillingAccountTermId(String  billingAccountTermId) {
this.billingAccountTermId = billingAccountTermId;
}

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
}

public String getTermTypeId() {
return termTypeId;
}

public void setTermTypeId(String  termTypeId) {
this.termTypeId = termTypeId;
}

public BigDecimal getTermValue() {
return termValue;
}

public void setTermValue(BigDecimal  termValue) {
this.termValue = termValue;
}

public Long getTermDays() {
return termDays;
}

public void setTermDays(Long  termDays) {
this.termDays = termDays;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}


public Map<String, Object> mapAttributeField() {
return BillingAccountTermMapper.map(this);
}
}
