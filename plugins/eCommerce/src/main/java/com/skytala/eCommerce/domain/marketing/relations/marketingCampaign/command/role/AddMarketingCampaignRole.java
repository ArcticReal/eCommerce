package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role.MarketingCampaignRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.role.MarketingCampaignRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.role.MarketingCampaignRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMarketingCampaignRole extends Command {

private MarketingCampaignRole elementToBeAdded;
public AddMarketingCampaignRole(MarketingCampaignRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MarketingCampaignRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("MarketingCampaignRole", elementToBeAdded.mapAttributeField());
addedElement = MarketingCampaignRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MarketingCampaignRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
