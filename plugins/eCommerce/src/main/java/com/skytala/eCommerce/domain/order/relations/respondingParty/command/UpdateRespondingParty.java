package com.skytala.eCommerce.domain.order.relations.respondingParty.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.respondingParty.event.RespondingPartyUpdated;
import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRespondingParty extends Command {

private RespondingParty elementToBeUpdated;

public UpdateRespondingParty(RespondingParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RespondingParty getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RespondingParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RespondingParty", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RespondingParty.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RespondingParty.class);
}
success = false;
}
Event resultingEvent = new RespondingPartyUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
