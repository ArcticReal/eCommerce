package com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.event.OrderItemGroupOrderUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.model.OrderItemGroupOrder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemGroupOrder extends Command {

private OrderItemGroupOrder elementToBeUpdated;

public UpdateOrderItemGroupOrder(OrderItemGroupOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemGroupOrder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemGroupOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemGroupOrder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemGroupOrder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemGroupOrder.class);
}
success = false;
}
Event resultingEvent = new OrderItemGroupOrderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
