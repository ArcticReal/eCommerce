package com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderAdjustmentAttribute extends Command {

private OrderAdjustmentAttribute elementToBeUpdated;

public UpdateOrderAdjustmentAttribute(OrderAdjustmentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderAdjustmentAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderAdjustmentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderAdjustmentAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderAdjustmentAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderAdjustmentAttribute.class);
}
success = false;
}
Event resultingEvent = new OrderAdjustmentAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
