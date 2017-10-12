package com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.event.EmplLeaveTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.model.EmplLeaveType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplLeaveType extends Command {

private EmplLeaveType elementToBeUpdated;

public UpdateEmplLeaveType(EmplLeaveType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplLeaveType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplLeaveType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplLeaveType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplLeaveType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplLeaveType.class);
}
success = false;
}
Event resultingEvent = new EmplLeaveTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
