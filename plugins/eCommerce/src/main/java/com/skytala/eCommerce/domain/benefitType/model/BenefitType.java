package com.skytala.eCommerce.domain.benefitType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.benefitType.mapper.BenefitTypeMapper;

public class BenefitType implements Serializable{

private static final long serialVersionUID = 1L;
private String benefitTypeId;
private String benefitName;
private String parentTypeId;
private Boolean hasTable;
private String description;
private BigDecimal employerPaidPercentage;

public String getBenefitTypeId() {
return benefitTypeId;
}

public void setBenefitTypeId(String  benefitTypeId) {
this.benefitTypeId = benefitTypeId;
}

public String getBenefitName() {
return benefitName;
}

public void setBenefitName(String  benefitName) {
this.benefitName = benefitName;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public BigDecimal getEmployerPaidPercentage() {
return employerPaidPercentage;
}

public void setEmployerPaidPercentage(BigDecimal  employerPaidPercentage) {
this.employerPaidPercentage = employerPaidPercentage;
}


public Map<String, Object> mapAttributeField() {
return BenefitTypeMapper.map(this);
}
}
