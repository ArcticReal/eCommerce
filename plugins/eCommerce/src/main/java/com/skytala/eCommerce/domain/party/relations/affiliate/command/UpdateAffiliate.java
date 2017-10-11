package com.skytala.eCommerce.domain.party.relations.affiliate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.affiliate.event.AffiliateUpdated;
import com.skytala.eCommerce.domain.party.relations.affiliate.model.Affiliate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAffiliate extends Command {

private Affiliate elementToBeUpdated;

public UpdateAffiliate(Affiliate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Affiliate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Affiliate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Affiliate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Affiliate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Affiliate.class);
}
success = false;
}
Event resultingEvent = new AffiliateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
