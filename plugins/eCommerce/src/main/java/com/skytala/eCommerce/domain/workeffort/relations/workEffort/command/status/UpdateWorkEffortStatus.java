package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortStatus extends Command {

private WorkEffortStatus elementToBeUpdated;

public UpdateWorkEffortStatus(WorkEffortStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortStatus.class);
}
success = false;
}
Event resultingEvent = new WorkEffortStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
