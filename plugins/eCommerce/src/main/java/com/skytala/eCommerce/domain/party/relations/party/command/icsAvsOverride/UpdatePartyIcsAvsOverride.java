package com.skytala.eCommerce.domain.party.relations.party.command.icsAvsOverride;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride.PartyIcsAvsOverrideUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.icsAvsOverride.PartyIcsAvsOverride;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyIcsAvsOverride extends Command {

private PartyIcsAvsOverride elementToBeUpdated;

public UpdatePartyIcsAvsOverride(PartyIcsAvsOverride elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyIcsAvsOverride getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyIcsAvsOverride elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyIcsAvsOverride", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyIcsAvsOverride.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyIcsAvsOverride.class);
}
success = false;
}
Event resultingEvent = new PartyIcsAvsOverrideUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
