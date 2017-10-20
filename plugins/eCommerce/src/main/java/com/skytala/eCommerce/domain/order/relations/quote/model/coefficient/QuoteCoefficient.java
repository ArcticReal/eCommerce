package com.skytala.eCommerce.domain.order.relations.quote.model.coefficient;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.coefficient.QuoteCoefficientMapper;

public class QuoteCoefficient implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteId;
private String coeffName;
private BigDecimal coeffValue;

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getCoeffName() {
return coeffName;
}

public void setCoeffName(String  coeffName) {
this.coeffName = coeffName;
}

public BigDecimal getCoeffValue() {
return coeffValue;
}

public void setCoeffValue(BigDecimal  coeffValue) {
this.coeffValue = coeffValue;
}


public Map<String, Object> mapAttributeField() {
return QuoteCoefficientMapper.map(this);
}
}
