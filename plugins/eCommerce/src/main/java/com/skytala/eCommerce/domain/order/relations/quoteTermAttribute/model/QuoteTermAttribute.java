package com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.mapper.QuoteTermAttributeMapper;

public class QuoteTermAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String termTypeId;
private String quoteId;
private String quoteItemSeqId;
private String attrName;
private Long attrValue;
private String attrDescription;

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

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public Long getAttrValue() {
return attrValue;
}

public void setAttrValue(Long  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return QuoteTermAttributeMapper.map(this);
}
}