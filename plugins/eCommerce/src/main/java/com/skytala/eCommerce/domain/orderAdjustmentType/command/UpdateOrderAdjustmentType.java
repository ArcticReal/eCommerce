package com.skytala.eCommerce.domain.orderAdjustmentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.orderAdjustmentType.event.OrderAdjustmentTypeUpdated;
import com.skytala.eCommerce.domain.orderAdjustmentType.model.OrderAdjustmentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderAdjustmentType extends Command {

private OrderAdjustmentType elementToBeUpdated;

public UpdateOrderAdjustmentType(OrderAdjustmentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderAdjustmentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderAdjustmentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderAdjustmentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderAdjustmentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderAdjustmentType.class);
}
success = false;
}
Event resultingEvent = new OrderAdjustmentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}