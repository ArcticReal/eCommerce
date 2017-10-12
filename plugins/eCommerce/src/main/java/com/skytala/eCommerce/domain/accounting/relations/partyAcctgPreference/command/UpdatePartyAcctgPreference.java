package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyAcctgPreference extends Command {

private PartyAcctgPreference elementToBeUpdated;

public UpdatePartyAcctgPreference(PartyAcctgPreference elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyAcctgPreference getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyAcctgPreference elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyAcctgPreference", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyAcctgPreference.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyAcctgPreference.class);
}
success = false;
}
Event resultingEvent = new PartyAcctgPreferenceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
