package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.TaxAuthorityMapper;

public class TaxAuthority implements Serializable{

private static final long serialVersionUID = 1L;
private String taxAuthGeoId;
private String taxAuthPartyId;
private Boolean requireTaxIdForExemption;
private String taxIdFormatPattern;
private Boolean includeTaxInPrice;

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public String getTaxAuthPartyId() {
return taxAuthPartyId;
}

public void setTaxAuthPartyId(String  taxAuthPartyId) {
this.taxAuthPartyId = taxAuthPartyId;
}

public Boolean getRequireTaxIdForExemption() {
return requireTaxIdForExemption;
}

public void setRequireTaxIdForExemption(Boolean  requireTaxIdForExemption) {
this.requireTaxIdForExemption = requireTaxIdForExemption;
}

public String getTaxIdFormatPattern() {
return taxIdFormatPattern;
}

public void setTaxIdFormatPattern(String  taxIdFormatPattern) {
this.taxIdFormatPattern = taxIdFormatPattern;
}

public Boolean getIncludeTaxInPrice() {
return includeTaxInPrice;
}

public void setIncludeTaxInPrice(Boolean  includeTaxInPrice) {
this.includeTaxInPrice = includeTaxInPrice;
}


public Map<String, Object> mapAttributeField() {
return TaxAuthorityMapper.map(this);
}
}
