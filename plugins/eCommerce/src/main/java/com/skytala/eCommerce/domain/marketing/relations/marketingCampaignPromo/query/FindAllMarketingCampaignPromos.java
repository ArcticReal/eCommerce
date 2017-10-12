
package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.event.MarketingCampaignPromoFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.mapper.MarketingCampaignPromoMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.model.MarketingCampaignPromo;


public class FindAllMarketingCampaignPromos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MarketingCampaignPromo> returnVal = new ArrayList<MarketingCampaignPromo>();
try{
List<GenericValue> results = delegator.findAll("MarketingCampaignPromo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MarketingCampaignPromoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MarketingCampaignPromoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
