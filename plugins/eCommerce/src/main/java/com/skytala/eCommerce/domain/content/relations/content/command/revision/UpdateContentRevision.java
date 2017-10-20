package com.skytala.eCommerce.domain.content.relations.content.command.revision;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.revision.ContentRevisionUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.revision.ContentRevision;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentRevision extends Command {

private ContentRevision elementToBeUpdated;

public UpdateContentRevision(ContentRevision elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentRevision getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentRevision elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentRevision", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentRevision.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentRevision.class);
}
success = false;
}
Event resultingEvent = new ContentRevisionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
