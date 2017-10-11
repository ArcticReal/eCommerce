package com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.event.PartyCarrierAccountUpdated;
import com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.model.PartyCarrierAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyCarrierAccount extends Command {

private PartyCarrierAccount elementToBeUpdated;

public UpdatePartyCarrierAccount(PartyCarrierAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyCarrierAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyCarrierAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyCarrierAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyCarrierAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyCarrierAccount.class);
}
success = false;
}
Event resultingEvent = new PartyCarrierAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
