package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.price;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price.MarketingCampaignPriceUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price.MarketingCampaignPrice;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMarketingCampaignPrice extends Command {

private MarketingCampaignPrice elementToBeUpdated;

public UpdateMarketingCampaignPrice(MarketingCampaignPrice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MarketingCampaignPrice getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MarketingCampaignPrice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MarketingCampaignPrice", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MarketingCampaignPrice.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketingCampaignPrice.class);
}
success = false;
}
Event resultingEvent = new MarketingCampaignPriceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
