package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateType.mapper.TaxAuthorityRateTypeMapper;

public class TaxAuthorityRateType implements Serializable{

private static final long serialVersionUID = 1L;
private String taxAuthorityRateTypeId;
private String description;

public String getTaxAuthorityRateTypeId() {
return taxAuthorityRateTypeId;
}

public void setTaxAuthorityRateTypeId(String  taxAuthorityRateTypeId) {
this.taxAuthorityRateTypeId = taxAuthorityRateTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TaxAuthorityRateTypeMapper.map(this);
}
}
