
package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event.MarketingCampaignRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.mapper.MarketingCampaignRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;


public class FindAllMarketingCampaignRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MarketingCampaignRole> returnVal = new ArrayList<MarketingCampaignRole>();
try{
List<GenericValue> results = delegator.findAll("MarketingCampaignRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MarketingCampaignRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MarketingCampaignRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
