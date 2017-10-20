package com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type.ReturnAdjustmentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnAdjustmentType extends Command {

private ReturnAdjustmentType elementToBeUpdated;

public UpdateReturnAdjustmentType(ReturnAdjustmentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnAdjustmentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnAdjustmentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnAdjustmentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnAdjustmentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnAdjustmentType.class);
}
success = false;
}
Event resultingEvent = new ReturnAdjustmentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
