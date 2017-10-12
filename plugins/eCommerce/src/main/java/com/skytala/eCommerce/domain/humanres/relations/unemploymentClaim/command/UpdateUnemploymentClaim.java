package com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimUpdated;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.model.UnemploymentClaim;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUnemploymentClaim extends Command {

private UnemploymentClaim elementToBeUpdated;

public UpdateUnemploymentClaim(UnemploymentClaim elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UnemploymentClaim getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UnemploymentClaim elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UnemploymentClaim", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UnemploymentClaim.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UnemploymentClaim.class);
}
success = false;
}
Event resultingEvent = new UnemploymentClaimUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
