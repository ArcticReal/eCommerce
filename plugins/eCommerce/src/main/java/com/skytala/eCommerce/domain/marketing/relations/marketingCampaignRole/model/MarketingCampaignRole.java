package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.mapper.MarketingCampaignRoleMapper;

public class MarketingCampaignRole implements Serializable{

private static final long serialVersionUID = 1L;
private String marketingCampaignId;
private String partyId;
private String roleTypeId;

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}


public Map<String, Object> mapAttributeField() {
return MarketingCampaignRoleMapper.map(this);
}
}
