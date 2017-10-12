package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.event.MarketingCampaignPriceAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.mapper.MarketingCampaignPriceMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignPrice.model.MarketingCampaignPrice;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMarketingCampaignPrice extends Command {

private MarketingCampaignPrice elementToBeAdded;
public AddMarketingCampaignPrice(MarketingCampaignPrice elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MarketingCampaignPrice addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("MarketingCampaignPrice", elementToBeAdded.mapAttributeField());
addedElement = MarketingCampaignPriceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MarketingCampaignPriceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
