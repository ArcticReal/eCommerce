package com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.workEffortProd;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd.WorkEffortDeliverableProdUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.workEffortProd.WorkEffortDeliverableProd;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortDeliverableProd extends Command {

private WorkEffortDeliverableProd elementToBeUpdated;

public UpdateWorkEffortDeliverableProd(WorkEffortDeliverableProd elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortDeliverableProd getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortDeliverableProd elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortDeliverableProd", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortDeliverableProd.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortDeliverableProd.class);
}
success = false;
}
Event resultingEvent = new WorkEffortDeliverableProdUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
