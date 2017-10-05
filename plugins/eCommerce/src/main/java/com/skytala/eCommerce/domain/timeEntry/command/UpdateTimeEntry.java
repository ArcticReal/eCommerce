package com.skytala.eCommerce.domain.timeEntry.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.timeEntry.event.TimeEntryUpdated;
import com.skytala.eCommerce.domain.timeEntry.model.TimeEntry;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTimeEntry extends Command {

private TimeEntry elementToBeUpdated;

public UpdateTimeEntry(TimeEntry elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TimeEntry getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TimeEntry elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TimeEntry", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TimeEntry.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TimeEntry.class);
}
success = false;
}
Event resultingEvent = new TimeEntryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
