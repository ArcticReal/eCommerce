package com.skytala.eCommerce.domain.party.relations.telecomNumber.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberUpdated;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTelecomNumber extends Command {

private TelecomNumber elementToBeUpdated;

public UpdateTelecomNumber(TelecomNumber elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TelecomNumber getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TelecomNumber elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TelecomNumber", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TelecomNumber.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TelecomNumber.class);
}
success = false;
}
Event resultingEvent = new TelecomNumberUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
