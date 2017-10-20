package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.promo;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo.MarketingCampaignPromoUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.promo.MarketingCampaignPromo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMarketingCampaignPromo extends Command {

private MarketingCampaignPromo elementToBeUpdated;

public UpdateMarketingCampaignPromo(MarketingCampaignPromo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MarketingCampaignPromo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MarketingCampaignPromo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MarketingCampaignPromo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MarketingCampaignPromo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketingCampaignPromo.class);
}
success = false;
}
Event resultingEvent = new MarketingCampaignPromoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
