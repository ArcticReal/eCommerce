package com.skytala.eCommerce.domain.returnAdjustment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.returnAdjustment.event.ReturnAdjustmentUpdated;
import com.skytala.eCommerce.domain.returnAdjustment.model.ReturnAdjustment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnAdjustment extends Command {

private ReturnAdjustment elementToBeUpdated;

public UpdateReturnAdjustment(ReturnAdjustment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnAdjustment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnAdjustment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnAdjustment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnAdjustment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnAdjustment.class);
}
success = false;
}
Event resultingEvent = new ReturnAdjustmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
