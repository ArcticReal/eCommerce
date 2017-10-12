package com.skytala.eCommerce.domain.content.relations.contentAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentAssoc.event.ContentAssocUpdated;
import com.skytala.eCommerce.domain.content.relations.contentAssoc.model.ContentAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentAssoc extends Command {

private ContentAssoc elementToBeUpdated;

public UpdateContentAssoc(ContentAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentAssoc.class);
}
success = false;
}
Event resultingEvent = new ContentAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
