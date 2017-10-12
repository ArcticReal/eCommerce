package com.skytala.eCommerce.domain.humanres.relations.emplLeave.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplLeave extends Command {

private EmplLeave elementToBeUpdated;

public UpdateEmplLeave(EmplLeave elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplLeave getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplLeave elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplLeave", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplLeave.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplLeave.class);
}
success = false;
}
Event resultingEvent = new EmplLeaveUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
