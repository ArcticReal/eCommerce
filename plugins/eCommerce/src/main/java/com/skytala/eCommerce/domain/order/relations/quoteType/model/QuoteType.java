package com.skytala.eCommerce.domain.order.relations.quoteType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteType.mapper.QuoteTypeMapper;

public class QuoteType implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getQuoteTypeId() {
return quoteTypeId;
}

public void setQuoteTypeId(String  quoteTypeId) {
this.quoteTypeId = quoteTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return QuoteTypeMapper.map(this);
}
}
