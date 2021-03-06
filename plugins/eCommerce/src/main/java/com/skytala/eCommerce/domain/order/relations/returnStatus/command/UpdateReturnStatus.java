package com.skytala.eCommerce.domain.order.relations.returnStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnStatus.event.ReturnStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.returnStatus.model.ReturnStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnStatus extends Command {

private ReturnStatus elementToBeUpdated;

public UpdateReturnStatus(ReturnStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnStatus.class);
}
success = false;
}
Event resultingEvent = new ReturnStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
