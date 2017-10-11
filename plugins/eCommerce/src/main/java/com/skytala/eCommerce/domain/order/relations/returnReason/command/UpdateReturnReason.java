package com.skytala.eCommerce.domain.order.relations.returnReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonUpdated;
import com.skytala.eCommerce.domain.order.relations.returnReason.model.ReturnReason;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnReason extends Command {

private ReturnReason elementToBeUpdated;

public UpdateReturnReason(ReturnReason elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnReason getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnReason elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnReason", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnReason.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnReason.class);
}
success = false;
}
Event resultingEvent = new ReturnReasonUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
