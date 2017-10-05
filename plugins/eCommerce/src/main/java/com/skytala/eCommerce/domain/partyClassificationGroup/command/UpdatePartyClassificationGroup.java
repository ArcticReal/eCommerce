package com.skytala.eCommerce.domain.partyClassificationGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.partyClassificationGroup.event.PartyClassificationGroupUpdated;
import com.skytala.eCommerce.domain.partyClassificationGroup.model.PartyClassificationGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyClassificationGroup extends Command {

private PartyClassificationGroup elementToBeUpdated;

public UpdatePartyClassificationGroup(PartyClassificationGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyClassificationGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyClassificationGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyClassificationGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyClassificationGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyClassificationGroup.class);
}
success = false;
}
Event resultingEvent = new PartyClassificationGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
