package com.skytala.eCommerce.domain.order.relations.returnItemBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.event.ReturnItemBillingUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.model.ReturnItemBilling;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnItemBilling extends Command {

private ReturnItemBilling elementToBeUpdated;

public UpdateReturnItemBilling(ReturnItemBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnItemBilling getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnItemBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnItemBilling", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnItemBilling.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnItemBilling.class);
}
success = false;
}
Event resultingEvent = new ReturnItemBillingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
