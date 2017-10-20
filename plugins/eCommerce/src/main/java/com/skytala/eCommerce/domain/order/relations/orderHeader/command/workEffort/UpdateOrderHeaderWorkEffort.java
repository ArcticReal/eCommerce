package com.skytala.eCommerce.domain.order.relations.orderHeader.command.workEffort;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderHeaderWorkEffort extends Command {

private OrderHeaderWorkEffort elementToBeUpdated;

public UpdateOrderHeaderWorkEffort(OrderHeaderWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderHeaderWorkEffort getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderHeaderWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderHeaderWorkEffort", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderHeaderWorkEffort.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderHeaderWorkEffort.class);
}
success = false;
}
Event resultingEvent = new OrderHeaderWorkEffortUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
