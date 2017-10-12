package com.skytala.eCommerce.domain.content.relations.contentAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.event.ContentAttributeUpdated;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.model.ContentAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentAttribute extends Command {

private ContentAttribute elementToBeUpdated;

public UpdateContentAttribute(ContentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentAttribute.class);
}
success = false;
}
Event resultingEvent = new ContentAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
