package com.skytala.eCommerce.domain.orderItemPriceInfo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.orderItemPriceInfo.event.OrderItemPriceInfoUpdated;
import com.skytala.eCommerce.domain.orderItemPriceInfo.model.OrderItemPriceInfo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemPriceInfo extends Command {

private OrderItemPriceInfo elementToBeUpdated;

public UpdateOrderItemPriceInfo(OrderItemPriceInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemPriceInfo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemPriceInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemPriceInfo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemPriceInfo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemPriceInfo.class);
}
success = false;
}
Event resultingEvent = new OrderItemPriceInfoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
