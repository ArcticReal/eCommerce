package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentUpdated;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkOrderItemFulfillment extends Command {

private WorkOrderItemFulfillment elementToBeUpdated;

public UpdateWorkOrderItemFulfillment(WorkOrderItemFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkOrderItemFulfillment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkOrderItemFulfillment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkOrderItemFulfillment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkOrderItemFulfillment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkOrderItemFulfillment.class);
}
success = false;
}
Event resultingEvent = new WorkOrderItemFulfillmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}