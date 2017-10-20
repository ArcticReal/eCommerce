package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.price.MarketingCampaignPriceMapper;

public class MarketingCampaignPrice implements Serializable{

private static final long serialVersionUID = 1L;
private String marketingCampaignId;
private String productPriceRuleId;

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getProductPriceRuleId() {
return productPriceRuleId;
}

public void setProductPriceRuleId(String  productPriceRuleId) {
this.productPriceRuleId = productPriceRuleId;
}


public Map<String, Object> mapAttributeField() {
return MarketingCampaignPriceMapper.map(this);
}
}
