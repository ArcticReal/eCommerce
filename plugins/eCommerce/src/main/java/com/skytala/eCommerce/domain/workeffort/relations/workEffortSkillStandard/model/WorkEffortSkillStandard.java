package com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.mapper.WorkEffortSkillStandardMapper;

public class WorkEffortSkillStandard implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String skillTypeId;
private BigDecimal estimatedNumPeople;
private BigDecimal estimatedDuration;
private BigDecimal estimatedCost;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getSkillTypeId() {
return skillTypeId;
}

public void setSkillTypeId(String  skillTypeId) {
this.skillTypeId = skillTypeId;
}

public BigDecimal getEstimatedNumPeople() {
return estimatedNumPeople;
}

public void setEstimatedNumPeople(BigDecimal  estimatedNumPeople) {
this.estimatedNumPeople = estimatedNumPeople;
}

public BigDecimal getEstimatedDuration() {
return estimatedDuration;
}

public void setEstimatedDuration(BigDecimal  estimatedDuration) {
this.estimatedDuration = estimatedDuration;
}

public BigDecimal getEstimatedCost() {
return estimatedCost;
}

public void setEstimatedCost(BigDecimal  estimatedCost) {
this.estimatedCost = estimatedCost;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortSkillStandardMapper.map(this);
}
}
