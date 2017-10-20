package com.skytala.eCommerce.domain.party.relations.party.command.group;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyGroup extends Command {

private PartyGroup elementToBeUpdated;

public UpdatePartyGroup(PartyGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyGroup.class);
}
success = false;
}
Event resultingEvent = new PartyGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
