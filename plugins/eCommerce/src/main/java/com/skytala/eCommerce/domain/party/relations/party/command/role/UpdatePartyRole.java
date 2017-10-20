package com.skytala.eCommerce.domain.party.relations.party.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.role.PartyRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.role.PartyRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyRole extends Command {

private PartyRole elementToBeUpdated;

public UpdatePartyRole(PartyRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyRole.class);
}
success = false;
}
Event resultingEvent = new PartyRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
