package com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.event.TimesheetRoleUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.timesheetRole.model.TimesheetRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTimesheetRole extends Command {

private TimesheetRole elementToBeUpdated;

public UpdateTimesheetRole(TimesheetRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TimesheetRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TimesheetRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TimesheetRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TimesheetRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TimesheetRole.class);
}
success = false;
}
Event resultingEvent = new TimesheetRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
