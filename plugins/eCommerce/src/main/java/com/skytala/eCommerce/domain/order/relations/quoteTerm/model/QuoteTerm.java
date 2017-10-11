package com.skytala.eCommerce.domain.order.relations.quoteTerm.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.mapper.QuoteTermMapper;

public class QuoteTerm implements Serializable{

private static final long serialVersionUID = 1L;
private String termTypeId;
private String quoteId;
private String quoteItemSeqId;
private Long termValue;
private String uomId;
private Long termDays;
private String textValue;
private String description;

public String getTermTypeId() {
return termTypeId;
}

public void setTermTypeId(String  termTypeId) {
this.termTypeId = termTypeId;
}

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getQuoteItemSeqId() {
return quoteItemSeqId;
}

public void setQuoteItemSeqId(String  quoteItemSeqId) {
this.quoteItemSeqId = quoteItemSeqId;
}

public Long getTermValue() {
return termValue;
}

public void setTermValue(Long  termValue) {
this.termValue = termValue;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public Long getTermDays() {
return termDays;
}

public void setTermDays(Long  termDays) {
this.termDays = termDays;
}

public String getTextValue() {
return textValue;
}

public void setTextValue(String  textValue) {
this.textValue = textValue;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return QuoteTermMapper.map(this);
}
}
