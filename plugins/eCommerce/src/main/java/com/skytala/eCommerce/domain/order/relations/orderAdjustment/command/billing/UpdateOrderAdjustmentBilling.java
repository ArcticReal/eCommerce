package com.skytala.eCommerce.domain.order.relations.orderAdjustment.command.billing;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.billing.OrderAdjustmentBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.billing.OrderAdjustmentBilling;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderAdjustmentBilling extends Command {

private OrderAdjustmentBilling elementToBeUpdated;

public UpdateOrderAdjustmentBilling(OrderAdjustmentBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderAdjustmentBilling getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderAdjustmentBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderAdjustmentBilling", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderAdjustmentBilling.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderAdjustmentBilling.class);
}
success = false;
}
Event resultingEvent = new OrderAdjustmentBillingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
