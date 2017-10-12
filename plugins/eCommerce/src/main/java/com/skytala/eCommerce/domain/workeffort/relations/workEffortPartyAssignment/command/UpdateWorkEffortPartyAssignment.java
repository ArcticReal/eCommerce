package com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event.WorkEffortPartyAssignmentUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.model.WorkEffortPartyAssignment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortPartyAssignment extends Command {

private WorkEffortPartyAssignment elementToBeUpdated;

public UpdateWorkEffortPartyAssignment(WorkEffortPartyAssignment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortPartyAssignment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortPartyAssignment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortPartyAssignment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortPartyAssignment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortPartyAssignment.class);
}
success = false;
}
Event resultingEvent = new WorkEffortPartyAssignmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
