package com.skytala.eCommerce.domain.accounting.relations.partyRate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyRate extends Command {

private PartyRate elementToBeUpdated;

public UpdatePartyRate(PartyRate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyRate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyRate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyRate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyRate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyRate.class);
}
success = false;
}
Event resultingEvent = new PartyRateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
