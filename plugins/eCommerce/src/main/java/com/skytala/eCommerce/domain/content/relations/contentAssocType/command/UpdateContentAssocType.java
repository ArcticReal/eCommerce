package com.skytala.eCommerce.domain.content.relations.contentAssocType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentAssocType.event.ContentAssocTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.contentAssocType.model.ContentAssocType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentAssocType extends Command {

private ContentAssocType elementToBeUpdated;

public UpdateContentAssocType(ContentAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentAssocType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentAssocType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentAssocType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentAssocType.class);
}
success = false;
}
Event resultingEvent = new ContentAssocTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
