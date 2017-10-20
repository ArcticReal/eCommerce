package com.skytala.eCommerce.domain.content.relations.content.command.purpose;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.purpose.ContentPurposeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentPurpose extends Command {

private ContentPurpose elementToBeUpdated;

public UpdateContentPurpose(ContentPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentPurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentPurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentPurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentPurpose.class);
}
success = false;
}
Event resultingEvent = new ContentPurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
