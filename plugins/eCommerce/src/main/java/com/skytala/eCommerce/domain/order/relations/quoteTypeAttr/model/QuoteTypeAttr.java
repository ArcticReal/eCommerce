package com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.mapper.QuoteTypeAttrMapper;

public class QuoteTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteTypeId;
private String attrName;
private String description;

public String getQuoteTypeId() {
return quoteTypeId;
}

public void setQuoteTypeId(String  quoteTypeId) {
this.quoteTypeId = quoteTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return QuoteTypeAttrMapper.map(this);
}
}
