package com.skytala.eCommerce.domain.content.relations.content.command.operation;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.operation.ContentOperationUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentOperation extends Command {

private ContentOperation elementToBeUpdated;

public UpdateContentOperation(ContentOperation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentOperation getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentOperation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentOperation", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentOperation.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentOperation.class);
}
success = false;
}
Event resultingEvent = new ContentOperationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
