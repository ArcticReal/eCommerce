package com.skytala.eCommerce.domain.order.relations.orderHeader.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderHeader extends Command {

private OrderHeader elementToBeUpdated;

public UpdateOrderHeader(OrderHeader elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderHeader getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderHeader elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderHeader", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderHeader.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderHeader.class);
}
success = false;
}
Event resultingEvent = new OrderHeaderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
