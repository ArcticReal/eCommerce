
package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.price;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price.MarketingCampaignPriceFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.price.MarketingCampaignPriceMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price.MarketingCampaignPrice;


public class FindAllMarketingCampaignPrices extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MarketingCampaignPrice> returnVal = new ArrayList<MarketingCampaignPrice>();
try{
List<GenericValue> results = delegator.findAll("MarketingCampaignPrice", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MarketingCampaignPriceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MarketingCampaignPriceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
