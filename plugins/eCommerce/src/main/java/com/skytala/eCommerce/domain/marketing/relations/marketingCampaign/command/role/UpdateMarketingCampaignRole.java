package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role.MarketingCampaignRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.role.MarketingCampaignRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMarketingCampaignRole extends Command {

private MarketingCampaignRole elementToBeUpdated;

public UpdateMarketingCampaignRole(MarketingCampaignRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MarketingCampaignRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MarketingCampaignRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MarketingCampaignRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MarketingCampaignRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketingCampaignRole.class);
}
success = false;
}
Event resultingEvent = new MarketingCampaignRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
