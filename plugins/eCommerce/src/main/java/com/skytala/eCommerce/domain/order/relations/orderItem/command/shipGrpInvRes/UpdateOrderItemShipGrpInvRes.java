package com.skytala.eCommerce.domain.order.relations.orderItem.command.shipGrpInvRes;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGrpInvRes.OrderItemShipGrpInvResUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGrpInvRes.OrderItemShipGrpInvRes;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemShipGrpInvRes extends Command {

private OrderItemShipGrpInvRes elementToBeUpdated;

public UpdateOrderItemShipGrpInvRes(OrderItemShipGrpInvRes elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemShipGrpInvRes getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemShipGrpInvRes elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemShipGrpInvRes", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemShipGrpInvRes.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemShipGrpInvRes.class);
}
success = false;
}
Event resultingEvent = new OrderItemShipGrpInvResUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
