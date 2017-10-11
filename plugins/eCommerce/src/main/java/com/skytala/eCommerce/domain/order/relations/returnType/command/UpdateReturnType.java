package com.skytala.eCommerce.domain.order.relations.returnType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnType.event.ReturnTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.returnType.model.ReturnType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnType extends Command {

private ReturnType elementToBeUpdated;

public UpdateReturnType(ReturnType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnType.class);
}
success = false;
}
Event resultingEvent = new ReturnTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
