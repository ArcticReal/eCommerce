package com.skytala.eCommerce.domain.content.relations.contentRevisionItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.event.ContentRevisionItemUpdated;
import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.model.ContentRevisionItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentRevisionItem extends Command {

private ContentRevisionItem elementToBeUpdated;

public UpdateContentRevisionItem(ContentRevisionItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentRevisionItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentRevisionItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentRevisionItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentRevisionItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentRevisionItem.class);
}
success = false;
}
Event resultingEvent = new ContentRevisionItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
