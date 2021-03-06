package com.skytala.eCommerce.domain.order.relations.workReqFulfType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.model.WorkReqFulfType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkReqFulfType extends Command {

private WorkReqFulfType elementToBeUpdated;

public UpdateWorkReqFulfType(WorkReqFulfType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkReqFulfType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkReqFulfType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkReqFulfType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkReqFulfType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkReqFulfType.class);
}
success = false;
}
Event resultingEvent = new WorkReqFulfTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
