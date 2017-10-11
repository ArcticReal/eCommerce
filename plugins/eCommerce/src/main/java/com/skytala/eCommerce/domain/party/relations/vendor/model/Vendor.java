package com.skytala.eCommerce.domain.party.relations.vendor.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.vendor.mapper.VendorMapper;

public class Vendor implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String manifestCompanyName;
private String manifestCompanyTitle;
private String manifestLogoUrl;
private String manifestPolicies;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getManifestCompanyName() {
return manifestCompanyName;
}

public void setManifestCompanyName(String  manifestCompanyName) {
this.manifestCompanyName = manifestCompanyName;
}

public String getManifestCompanyTitle() {
return manifestCompanyTitle;
}

public void setManifestCompanyTitle(String  manifestCompanyTitle) {
this.manifestCompanyTitle = manifestCompanyTitle;
}

public String getManifestLogoUrl() {
return manifestLogoUrl;
}

public void setManifestLogoUrl(String  manifestLogoUrl) {
this.manifestLogoUrl = manifestLogoUrl;
}

public String getManifestPolicies() {
return manifestPolicies;
}

public void setManifestPolicies(String  manifestPolicies) {
this.manifestPolicies = manifestPolicies;
}


public Map<String, Object> mapAttributeField() {
return VendorMapper.map(this);
}
}
