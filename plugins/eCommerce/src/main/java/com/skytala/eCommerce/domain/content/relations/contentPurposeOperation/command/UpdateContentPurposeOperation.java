package com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.event.ContentPurposeOperationUpdated;
import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.model.ContentPurposeOperation;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentPurposeOperation extends Command {

private ContentPurposeOperation elementToBeUpdated;

public UpdateContentPurposeOperation(ContentPurposeOperation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentPurposeOperation getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentPurposeOperation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentPurposeOperation", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentPurposeOperation.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentPurposeOperation.class);
}
success = false;
}
Event resultingEvent = new ContentPurposeOperationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
