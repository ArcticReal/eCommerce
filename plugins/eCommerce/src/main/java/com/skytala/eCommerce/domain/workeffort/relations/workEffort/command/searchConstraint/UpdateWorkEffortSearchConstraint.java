package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortSearchConstraint extends Command {

private WorkEffortSearchConstraint elementToBeUpdated;

public UpdateWorkEffortSearchConstraint(WorkEffortSearchConstraint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortSearchConstraint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortSearchConstraint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortSearchConstraint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortSearchConstraint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortSearchConstraint.class);
}
success = false;
}
Event resultingEvent = new WorkEffortSearchConstraintUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
