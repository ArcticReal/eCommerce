package com.skytala.eCommerce.domain.content.relations.content.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.type.ContentTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentType extends Command {

private ContentType elementToBeUpdated;

public UpdateContentType(ContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentType.class);
}
success = false;
}
Event resultingEvent = new ContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
