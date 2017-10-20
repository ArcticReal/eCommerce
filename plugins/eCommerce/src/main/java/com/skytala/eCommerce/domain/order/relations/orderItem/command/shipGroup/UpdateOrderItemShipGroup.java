package com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemShipGroup extends Command {

private OrderItemShipGroup elementToBeUpdated;

public UpdateOrderItemShipGroup(OrderItemShipGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemShipGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemShipGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemShipGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemShipGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemShipGroup.class);
}
success = false;
}
Event resultingEvent = new OrderItemShipGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
