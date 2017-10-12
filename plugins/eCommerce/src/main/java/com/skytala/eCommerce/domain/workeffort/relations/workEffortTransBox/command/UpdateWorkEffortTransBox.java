package com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.event.WorkEffortTransBoxUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.model.WorkEffortTransBox;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortTransBox extends Command {

private WorkEffortTransBox elementToBeUpdated;

public UpdateWorkEffortTransBox(WorkEffortTransBox elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortTransBox getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortTransBox elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortTransBox", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortTransBox.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortTransBox.class);
}
success = false;
}
Event resultingEvent = new WorkEffortTransBoxUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
