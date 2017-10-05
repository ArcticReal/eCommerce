package com.skytala.eCommerce.domain.partyClassificationType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.partyClassificationType.event.PartyClassificationTypeUpdated;
import com.skytala.eCommerce.domain.partyClassificationType.model.PartyClassificationType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyClassificationType extends Command {

private PartyClassificationType elementToBeUpdated;

public UpdatePartyClassificationType(PartyClassificationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyClassificationType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyClassificationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyClassificationType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyClassificationType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyClassificationType.class);
}
success = false;
}
Event resultingEvent = new PartyClassificationTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
