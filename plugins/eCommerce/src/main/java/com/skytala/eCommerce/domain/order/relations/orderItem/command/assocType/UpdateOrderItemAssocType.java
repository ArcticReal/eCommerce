package com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderItemAssocType extends Command {

private OrderItemAssocType elementToBeUpdated;

public UpdateOrderItemAssocType(OrderItemAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderItemAssocType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderItemAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderItemAssocType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderItemAssocType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderItemAssocType.class);
}
success = false;
}
Event resultingEvent = new OrderItemAssocTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
