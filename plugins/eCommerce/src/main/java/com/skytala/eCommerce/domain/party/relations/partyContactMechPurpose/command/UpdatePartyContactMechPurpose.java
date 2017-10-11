package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.event.PartyContactMechPurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model.PartyContactMechPurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyContactMechPurpose extends Command {

private PartyContactMechPurpose elementToBeUpdated;

public UpdatePartyContactMechPurpose(PartyContactMechPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyContactMechPurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyContactMechPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyContactMechPurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyContactMechPurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyContactMechPurpose.class);
}
success = false;
}
Event resultingEvent = new PartyContactMechPurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
