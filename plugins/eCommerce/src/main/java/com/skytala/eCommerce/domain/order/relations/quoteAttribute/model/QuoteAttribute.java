package com.skytala.eCommerce.domain.order.relations.quoteAttribute.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteAttribute.mapper.QuoteAttributeMapper;

public class QuoteAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
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
return QuoteAttributeMapper.map(this);
}
}
