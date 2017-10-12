package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityUpdated;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateValidResponsibility extends Command {

private ValidResponsibility elementToBeUpdated;

public UpdateValidResponsibility(ValidResponsibility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ValidResponsibility getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ValidResponsibility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ValidResponsibility", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ValidResponsibility.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ValidResponsibility.class);
}
success = false;
}
Event resultingEvent = new ValidResponsibilityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
