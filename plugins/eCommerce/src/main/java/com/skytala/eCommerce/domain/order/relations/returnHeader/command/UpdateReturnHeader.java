package com.skytala.eCommerce.domain.order.relations.returnHeader.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderUpdated;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnHeader extends Command {

private ReturnHeader elementToBeUpdated;

public UpdateReturnHeader(ReturnHeader elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnHeader getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnHeader elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnHeader", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnHeader.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnHeader.class);
}
success = false;
}
Event resultingEvent = new ReturnHeaderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
