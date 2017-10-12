package com.skytala.eCommerce.domain.workeffort.relations.timesheet.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.TimesheetUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.Timesheet;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTimesheet extends Command {

private Timesheet elementToBeUpdated;

public UpdateTimesheet(Timesheet elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Timesheet getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Timesheet elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Timesheet", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Timesheet.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Timesheet.class);
}
success = false;
}
Event resultingEvent = new TimesheetUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
