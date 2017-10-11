package com.skytala.eCommerce.domain.order.relations.orderContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderContent extends Command {

private OrderContent elementToBeUpdated;

public UpdateOrderContent(OrderContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderContent.class);
}
success = false;
}
Event resultingEvent = new OrderContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
