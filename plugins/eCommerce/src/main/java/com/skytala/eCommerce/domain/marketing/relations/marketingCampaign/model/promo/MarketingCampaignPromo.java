package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.promo;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.promo.MarketingCampaignPromoMapper;

public class MarketingCampaignPromo implements Serializable{

private static final long serialVersionUID = 1L;
private String marketingCampaignId;
private String productPromoId;

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getProductPromoId() {
return productPromoId;
}

public void setProductPromoId(String  productPromoId) {
this.productPromoId = productPromoId;
}


public Map<String, Object> mapAttributeField() {
return MarketingCampaignPromoMapper.map(this);
}
}
