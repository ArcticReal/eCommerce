package com.skytala.eCommerce.domain.party.relations.party.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.status.PartyStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyStatus extends Command {

private PartyStatus elementToBeUpdated;

public UpdatePartyStatus(PartyStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyStatus.class);
}
success = false;
}
Event resultingEvent = new PartyStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
