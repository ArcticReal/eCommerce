package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.event.WorkEffortEventReminderUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortEventReminder extends Command {

private WorkEffortEventReminder elementToBeUpdated;

public UpdateWorkEffortEventReminder(WorkEffortEventReminder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortEventReminder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortEventReminder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortEventReminder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortEventReminder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortEventReminder.class);
}
success = false;
}
Event resultingEvent = new WorkEffortEventReminderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
