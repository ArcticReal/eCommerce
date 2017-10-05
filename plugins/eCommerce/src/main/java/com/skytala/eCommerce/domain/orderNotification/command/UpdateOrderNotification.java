package com.skytala.eCommerce.domain.orderNotification.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.orderNotification.event.OrderNotificationUpdated;
import com.skytala.eCommerce.domain.orderNotification.model.OrderNotification;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderNotification extends Command {

private OrderNotification elementToBeUpdated;

public UpdateOrderNotification(OrderNotification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderNotification getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderNotification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderNotification", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderNotification.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderNotification.class);
}
success = false;
}
Event resultingEvent = new OrderNotificationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
