package com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGroupAssoc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc.OrderItemShipGroupAssocUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroupAssoc.OrderItemShipGroupAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemShipGroupAssoc extends Command {

private OrderItemShipGroupAssoc elementToBeUpdated;

public UpdateOrderItemShipGroupAssoc(OrderItemShipGroupAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemShipGroupAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemShipGroupAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemShipGroupAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemShipGroupAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemShipGroupAssoc.class);
}
success = false;
}
Event resultingEvent = new OrderItemShipGroupAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
