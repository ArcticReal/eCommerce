package com.skytala.eCommerce.domain.order.relations.orderItemBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemBilling extends Command {

private OrderItemBilling elementToBeUpdated;

public UpdateOrderItemBilling(OrderItemBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemBilling getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemBilling", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemBilling.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemBilling.class);
}
success = false;
}
Event resultingEvent = new OrderItemBillingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
