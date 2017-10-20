package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.stage.SalesOpportunityStageMapper;

public class SalesOpportunityStage implements Serializable{

private static final long serialVersionUID = 1L;
private String opportunityStageId;
private String description;
private BigDecimal defaultProbability;
private Long sequenceNum;

public String getOpportunityStageId() {
return opportunityStageId;
}

public void setOpportunityStageId(String  opportunityStageId) {
this.opportunityStageId = opportunityStageId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public BigDecimal getDefaultProbability() {
return defaultProbability;
}

public void setDefaultProbability(BigDecimal  defaultProbability) {
this.defaultProbability = defaultProbability;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityStageMapper.map(this);
}
}
