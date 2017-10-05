package com.skytala.eCommerce.domain.returnHeaderType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.returnHeaderType.event.ReturnHeaderTypeUpdated;
import com.skytala.eCommerce.domain.returnHeaderType.model.ReturnHeaderType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnHeaderType extends Command {

private ReturnHeaderType elementToBeUpdated;

public UpdateReturnHeaderType(ReturnHeaderType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnHeaderType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnHeaderType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnHeaderType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnHeaderType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnHeaderType.class);
}
success = false;
}
Event resultingEvent = new ReturnHeaderTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
