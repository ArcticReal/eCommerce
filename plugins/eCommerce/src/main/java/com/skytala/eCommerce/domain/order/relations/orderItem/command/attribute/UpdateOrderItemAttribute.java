package com.skytala.eCommerce.domain.order.relations.orderItem.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.attribute.OrderItemAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.attribute.OrderItemAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemAttribute extends Command {

private OrderItemAttribute elementToBeUpdated;

public UpdateOrderItemAttribute(OrderItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemAttribute.class);
}
success = false;
}
Event resultingEvent = new OrderItemAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
