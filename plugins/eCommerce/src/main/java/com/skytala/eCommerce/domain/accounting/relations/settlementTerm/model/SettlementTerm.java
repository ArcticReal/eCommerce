package com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.mapper.SettlementTermMapper;

public class SettlementTerm implements Serializable{

private static final long serialVersionUID = 1L;
private String settlementTermId;
private String termName;
private Long termValue;
private String uomId;

public String getSettlementTermId() {
return settlementTermId;
}

public void setSettlementTermId(String  settlementTermId) {
this.settlementTermId = settlementTermId;
}

public String getTermName() {
return termName;
}

public void setTermName(String  termName) {
this.termName = termName;
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


public Map<String, Object> mapAttributeField() {
return SettlementTermMapper.map(this);
}
}
