package com.skytala.eCommerce.domain.requirement.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.requirement.event.RequirementUpdated;
import com.skytala.eCommerce.domain.requirement.model.Requirement;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRequirement extends Command {

private Requirement elementToBeUpdated;

public UpdateRequirement(Requirement elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Requirement getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Requirement elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Requirement", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Requirement.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Requirement.class);
}
success = false;
}
Event resultingEvent = new RequirementUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
