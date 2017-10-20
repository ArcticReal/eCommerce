package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.competitor;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.competitor.SalesOpportunityCompetitorMapper;

public class SalesOpportunityCompetitor implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityId;
private String competitorPartyId;
private String positionEnumId;
private String strengths;
private String weaknesses;

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
}

public String getCompetitorPartyId() {
return competitorPartyId;
}

public void setCompetitorPartyId(String  competitorPartyId) {
this.competitorPartyId = competitorPartyId;
}

public String getPositionEnumId() {
return positionEnumId;
}

public void setPositionEnumId(String  positionEnumId) {
this.positionEnumId = positionEnumId;
}

public String getStrengths() {
return strengths;
}

public void setStrengths(String  strengths) {
this.strengths = strengths;
}

public String getWeaknesses() {
return weaknesses;
}

public void setWeaknesses(String  weaknesses) {
this.weaknesses = weaknesses;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityCompetitorMapper.map(this);
}
}
