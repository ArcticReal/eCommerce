package com.skytala.eCommerce.domain.order.relations.orderShipment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderShipment extends Command {

private OrderShipment elementToBeUpdated;

public UpdateOrderShipment(OrderShipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderShipment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderShipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderShipment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderShipment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderShipment.class);
}
success = false;
}
Event resultingEvent = new OrderShipmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
