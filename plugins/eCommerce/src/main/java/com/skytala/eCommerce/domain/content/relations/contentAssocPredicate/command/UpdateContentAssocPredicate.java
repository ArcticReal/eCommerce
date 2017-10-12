package com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.event.ContentAssocPredicateUpdated;
import com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.model.ContentAssocPredicate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentAssocPredicate extends Command {

private ContentAssocPredicate elementToBeUpdated;

public UpdateContentAssocPredicate(ContentAssocPredicate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentAssocPredicate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentAssocPredicate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentAssocPredicate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentAssocPredicate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentAssocPredicate.class);
}
success = false;
}
Event resultingEvent = new ContentAssocPredicateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
