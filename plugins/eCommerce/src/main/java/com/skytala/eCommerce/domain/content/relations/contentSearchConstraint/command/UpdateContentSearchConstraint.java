package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event.ContentSearchConstraintUpdated;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentSearchConstraint extends Command {

private ContentSearchConstraint elementToBeUpdated;

public UpdateContentSearchConstraint(ContentSearchConstraint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentSearchConstraint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentSearchConstraint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentSearchConstraint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentSearchConstraint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentSearchConstraint.class);
}
success = false;
}
Event resultingEvent = new ContentSearchConstraintUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
