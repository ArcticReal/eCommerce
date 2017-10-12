package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyTaxAuthInfo extends Command {

private PartyTaxAuthInfo elementToBeUpdated;

public UpdatePartyTaxAuthInfo(PartyTaxAuthInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyTaxAuthInfo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyTaxAuthInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyTaxAuthInfo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyTaxAuthInfo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyTaxAuthInfo.class);
}
success = false;
}
Event resultingEvent = new PartyTaxAuthInfoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
