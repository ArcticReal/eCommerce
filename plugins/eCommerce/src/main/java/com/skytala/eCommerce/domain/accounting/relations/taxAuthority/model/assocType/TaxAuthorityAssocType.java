package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assocType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.assocType.TaxAuthorityAssocTypeMapper;

public class TaxAuthorityAssocType implements Serializable{

private static final long serialVersionUID = 1L;
private String taxAuthorityAssocTypeId;
private String description;

public String getTaxAuthorityAssocTypeId() {
return taxAuthorityAssocTypeId;
}

public void setTaxAuthorityAssocTypeId(String  taxAuthorityAssocTypeId) {
this.taxAuthorityAssocTypeId = taxAuthorityAssocTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TaxAuthorityAssocTypeMapper.map(this);
}
}
