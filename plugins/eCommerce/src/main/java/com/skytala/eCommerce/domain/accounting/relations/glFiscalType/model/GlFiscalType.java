package com.skytala.eCommerce.domain.accounting.relations.glFiscalType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.mapper.GlFiscalTypeMapper;

public class GlFiscalType implements Serializable{

private static final long serialVersionUID = 1L;
private String glFiscalTypeId;
private String description;

public String getGlFiscalTypeId() {
return glFiscalTypeId;
}

public void setGlFiscalTypeId(String  glFiscalTypeId) {
this.glFiscalTypeId = glFiscalTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return GlFiscalTypeMapper.map(this);
}
}
