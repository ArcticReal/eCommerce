package com.skytala.eCommerce.domain.order.relations.orderType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderType.model.OrderType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderType extends Command {

private OrderType elementToBeUpdated;

public UpdateOrderType(OrderType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderType.class);
}
success = false;
}
Event resultingEvent = new OrderTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
