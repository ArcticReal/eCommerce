package com.skytala.eCommerce.domain.party.relations.partyContactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.event.PartyContactMechUpdated;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.model.PartyContactMech;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyContactMech extends Command {

private PartyContactMech elementToBeUpdated;

public UpdatePartyContactMech(PartyContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyContactMech getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyContactMech", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyContactMech.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyContactMech.class);
}
success = false;
}
Event resultingEvent = new PartyContactMechUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
