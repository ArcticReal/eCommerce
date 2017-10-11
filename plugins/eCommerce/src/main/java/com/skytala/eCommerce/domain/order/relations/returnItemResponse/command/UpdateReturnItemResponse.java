package com.skytala.eCommerce.domain.order.relations.returnItemResponse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnItemResponse.event.ReturnItemResponseUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItemResponse.model.ReturnItemResponse;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnItemResponse extends Command {

private ReturnItemResponse elementToBeUpdated;

public UpdateReturnItemResponse(ReturnItemResponse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnItemResponse getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnItemResponse elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnItemResponse", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnItemResponse.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnItemResponse.class);
}
success = false;
}
Event resultingEvent = new ReturnItemResponseUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
