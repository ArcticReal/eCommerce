package com.skytala.eCommerce.domain.order.relations.orderContent.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.type.OrderContentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderContentType extends Command {

private OrderContentType elementToBeUpdated;

public UpdateOrderContentType(OrderContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderContentType.class);
}
success = false;
}
Event resultingEvent = new OrderContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
