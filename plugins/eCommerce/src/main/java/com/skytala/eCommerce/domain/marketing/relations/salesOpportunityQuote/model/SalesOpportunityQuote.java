package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.mapper.SalesOpportunityQuoteMapper;

public class SalesOpportunityQuote implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityId;
private String quoteId;

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
}

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityQuoteMapper.map(this);
}
}
