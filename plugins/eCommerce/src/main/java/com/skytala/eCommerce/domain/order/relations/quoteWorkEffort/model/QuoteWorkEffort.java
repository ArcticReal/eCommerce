package com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.mapper.QuoteWorkEffortMapper;

public class QuoteWorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteId;
private String workEffortId;

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return QuoteWorkEffortMapper.map(this);
}
}
