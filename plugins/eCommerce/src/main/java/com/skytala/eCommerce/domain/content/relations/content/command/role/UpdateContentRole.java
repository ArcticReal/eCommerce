package com.skytala.eCommerce.domain.content.relations.content.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.role.ContentRoleUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.role.ContentRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentRole extends Command {

private ContentRole elementToBeUpdated;

public UpdateContentRole(ContentRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentRole.class);
}
success = false;
}
Event resultingEvent = new ContentRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
