package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.mapper.FixedAssetTypeGlAccountMapper;

public class FixedAssetTypeGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetTypeId;
private String fixedAssetId;
private String organizationPartyId;
private String assetGlAccountId;
private String accDepGlAccountId;
private String depGlAccountId;
private String profitGlAccountId;
private String lossGlAccountId;

public String getFixedAssetTypeId() {
return fixedAssetTypeId;
}

public void setFixedAssetTypeId(String  fixedAssetTypeId) {
this.fixedAssetTypeId = fixedAssetTypeId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getAssetGlAccountId() {
return assetGlAccountId;
}

public void setAssetGlAccountId(String  assetGlAccountId) {
this.assetGlAccountId = assetGlAccountId;
}

public String getAccDepGlAccountId() {
return accDepGlAccountId;
}

public void setAccDepGlAccountId(String  accDepGlAccountId) {
this.accDepGlAccountId = accDepGlAccountId;
}

public String getDepGlAccountId() {
return depGlAccountId;
}

public void setDepGlAccountId(String  depGlAccountId) {
this.depGlAccountId = depGlAccountId;
}

public String getProfitGlAccountId() {
return profitGlAccountId;
}

public void setProfitGlAccountId(String  profitGlAccountId) {
this.profitGlAccountId = profitGlAccountId;
}

public String getLossGlAccountId() {
return lossGlAccountId;
}

public void setLossGlAccountId(String  lossGlAccountId) {
this.lossGlAccountId = lossGlAccountId;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetTypeGlAccountMapper.map(this);
}
}
