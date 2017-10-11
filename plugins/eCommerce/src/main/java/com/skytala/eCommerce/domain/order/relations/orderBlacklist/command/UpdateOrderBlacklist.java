package com.skytala.eCommerce.domain.order.relations.orderBlacklist.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.OrderBlacklistUpdated;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderBlacklist extends Command {

private OrderBlacklist elementToBeUpdated;

public UpdateOrderBlacklist(OrderBlacklist elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderBlacklist getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderBlacklist elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderBlacklist", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderBlacklist.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderBlacklist.class);
}
success = false;
}
Event resultingEvent = new OrderBlacklistUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
