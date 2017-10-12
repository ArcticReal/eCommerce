package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event.PartyGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyGlAccount extends Command {

private PartyGlAccount elementToBeUpdated;

public UpdatePartyGlAccount(PartyGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyGlAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyGlAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyGlAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyGlAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyGlAccount.class);
}
success = false;
}
Event resultingEvent = new PartyGlAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
