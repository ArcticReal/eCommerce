package com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.event.WorkRequirementFulfillmentUpdated;
import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.model.WorkRequirementFulfillment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkRequirementFulfillment extends Command {

private WorkRequirementFulfillment elementToBeUpdated;

public UpdateWorkRequirementFulfillment(WorkRequirementFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkRequirementFulfillment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkRequirementFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkRequirementFulfillment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkRequirementFulfillment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkRequirementFulfillment.class);
}
success = false;
}
Event resultingEvent = new WorkRequirementFulfillmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
