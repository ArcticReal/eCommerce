package com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.event.EmplPositionResponsibilityUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.model.EmplPositionResponsibility;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplPositionResponsibility extends Command {

private EmplPositionResponsibility elementToBeUpdated;

public UpdateEmplPositionResponsibility(EmplPositionResponsibility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplPositionResponsibility getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplPositionResponsibility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplPositionResponsibility", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplPositionResponsibility.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplPositionResponsibility.class);
}
success = false;
}
Event resultingEvent = new EmplPositionResponsibilityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
