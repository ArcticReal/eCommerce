package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.MarketingCampaign;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMarketingCampaign extends Command {

private MarketingCampaign elementToBeUpdated;

public UpdateMarketingCampaign(MarketingCampaign elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MarketingCampaign getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MarketingCampaign elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MarketingCampaign", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MarketingCampaign.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketingCampaign.class);
}
success = false;
}
Event resultingEvent = new MarketingCampaignUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
