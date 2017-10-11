package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentAdded;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.mapper.WorkOrderItemFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkOrderItemFulfillment extends Command {

private WorkOrderItemFulfillment elementToBeAdded;
public AddWorkOrderItemFulfillment(WorkOrderItemFulfillment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkOrderItemFulfillment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("WorkOrderItemFulfillment"));
GenericValue newValue = delegator.makeValue("WorkOrderItemFulfillment", elementToBeAdded.mapAttributeField());
addedElement = WorkOrderItemFulfillmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkOrderItemFulfillmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
