package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.MarketingCampaignMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.MarketingCampaign;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMarketingCampaign extends Command {

private MarketingCampaign elementToBeAdded;
public AddMarketingCampaign(MarketingCampaign elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MarketingCampaign addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMarketingCampaignId(delegator.getNextSeqId("MarketingCampaign"));
GenericValue newValue = delegator.makeValue("MarketingCampaign", elementToBeAdded.mapAttributeField());
addedElement = MarketingCampaignMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MarketingCampaignAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
