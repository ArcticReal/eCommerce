package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyBenefit extends Command {

private PartyBenefit elementToBeUpdated;

public UpdatePartyBenefit(PartyBenefit elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyBenefit getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyBenefit elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyBenefit", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyBenefit.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyBenefit.class);
}
success = false;
}
Event resultingEvent = new PartyBenefitUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
