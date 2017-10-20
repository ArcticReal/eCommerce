package com.skytala.eCommerce.domain.order.relations.orderItem.command.change;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.change.OrderItemChangeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.change.OrderItemChange;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemChange extends Command {

private OrderItemChange elementToBeUpdated;

public UpdateOrderItemChange(OrderItemChange elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemChange getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemChange elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemChange", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemChange.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemChange.class);
}
success = false;
}
Event resultingEvent = new OrderItemChangeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
