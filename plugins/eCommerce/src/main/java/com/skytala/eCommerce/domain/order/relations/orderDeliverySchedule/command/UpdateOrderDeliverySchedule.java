package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleUpdated;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderDeliverySchedule extends Command {

private OrderDeliverySchedule elementToBeUpdated;

public UpdateOrderDeliverySchedule(OrderDeliverySchedule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderDeliverySchedule getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderDeliverySchedule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderDeliverySchedule", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderDeliverySchedule.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderDeliverySchedule.class);
}
success = false;
}
Event resultingEvent = new OrderDeliveryScheduleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
