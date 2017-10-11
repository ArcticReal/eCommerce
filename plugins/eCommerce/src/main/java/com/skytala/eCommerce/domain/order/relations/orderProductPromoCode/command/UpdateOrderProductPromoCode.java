package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderProductPromoCode extends Command {

private OrderProductPromoCode elementToBeUpdated;

public UpdateOrderProductPromoCode(OrderProductPromoCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderProductPromoCode getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderProductPromoCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderProductPromoCode", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderProductPromoCode.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderProductPromoCode.class);
}
success = false;
}
Event resultingEvent = new OrderProductPromoCodeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
