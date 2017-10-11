package com.skytala.eCommerce.domain.order.relations.orderContactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechUpdated;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderContactMech extends Command {

private OrderContactMech elementToBeUpdated;

public UpdateOrderContactMech(OrderContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderContactMech getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderContactMech", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderContactMech.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderContactMech.class);
}
success = false;
}
Event resultingEvent = new OrderContactMechUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
