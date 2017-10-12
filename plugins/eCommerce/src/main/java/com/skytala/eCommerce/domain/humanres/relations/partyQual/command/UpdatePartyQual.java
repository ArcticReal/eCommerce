package com.skytala.eCommerce.domain.humanres.relations.partyQual.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyQual extends Command {

private PartyQual elementToBeUpdated;

public UpdatePartyQual(PartyQual elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyQual getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyQual elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyQual", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyQual.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyQual.class);
}
success = false;
}
Event resultingEvent = new PartyQualUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
