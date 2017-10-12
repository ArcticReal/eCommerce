package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.event.MarketingCampaignPromoAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.mapper.MarketingCampaignPromoMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPromo.model.MarketingCampaignPromo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMarketingCampaignPromo extends Command {

private MarketingCampaignPromo elementToBeAdded;
public AddMarketingCampaignPromo(MarketingCampaignPromo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MarketingCampaignPromo addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("MarketingCampaignPromo", elementToBeAdded.mapAttributeField());
addedElement = MarketingCampaignPromoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MarketingCampaignPromoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
