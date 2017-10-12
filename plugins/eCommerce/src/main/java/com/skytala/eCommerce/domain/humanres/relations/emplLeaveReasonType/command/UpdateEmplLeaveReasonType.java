package com.skytala.eCommerce.domain.humanres.relations.emplLeaveReasonType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveReasonType.event.EmplLeaveReasonTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveReasonType.model.EmplLeaveReasonType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplLeaveReasonType extends Command {

private EmplLeaveReasonType elementToBeUpdated;

public UpdateEmplLeaveReasonType(EmplLeaveReasonType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplLeaveReasonType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplLeaveReasonType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplLeaveReasonType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplLeaveReasonType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplLeaveReasonType.class);
}
success = false;
}
Event resultingEvent = new EmplLeaveReasonTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
