package com.skytala.eCommerce.domain.orderBlacklistType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.orderBlacklistType.event.OrderBlacklistTypeUpdated;
import com.skytala.eCommerce.domain.orderBlacklistType.model.OrderBlacklistType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderBlacklistType extends Command {

private OrderBlacklistType elementToBeUpdated;

public UpdateOrderBlacklistType(OrderBlacklistType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderBlacklistType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderBlacklistType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderBlacklistType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderBlacklistType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderBlacklistType.class);
}
success = false;
}
Event resultingEvent = new OrderBlacklistTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
