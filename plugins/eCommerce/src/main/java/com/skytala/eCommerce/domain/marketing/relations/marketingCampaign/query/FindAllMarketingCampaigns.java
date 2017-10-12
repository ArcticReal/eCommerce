
package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.MarketingCampaignMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.MarketingCampaign;


public class FindAllMarketingCampaigns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MarketingCampaign> returnVal = new ArrayList<MarketingCampaign>();
try{
List<GenericValue> results = delegator.findAll("MarketingCampaign", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MarketingCampaignMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MarketingCampaignFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
