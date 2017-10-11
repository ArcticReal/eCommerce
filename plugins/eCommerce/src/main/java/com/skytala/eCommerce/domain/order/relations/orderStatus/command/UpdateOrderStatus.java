package com.skytala.eCommerce.domain.order.relations.orderStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderStatus extends Command {

private OrderStatus elementToBeUpdated;

public UpdateOrderStatus(OrderStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderStatus.class);
}
success = false;
}
Event resultingEvent = new OrderStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
