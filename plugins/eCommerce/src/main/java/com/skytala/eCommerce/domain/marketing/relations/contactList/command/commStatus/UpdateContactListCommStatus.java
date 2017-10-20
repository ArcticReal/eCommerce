package com.skytala.eCommerce.domain.marketing.relations.contactList.command.commStatus;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus.ContactListCommStatusUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus.ContactListCommStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactListCommStatus extends Command {

private ContactListCommStatus elementToBeUpdated;

public UpdateContactListCommStatus(ContactListCommStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactListCommStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactListCommStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactListCommStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactListCommStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactListCommStatus.class);
}
success = false;
}
Event resultingEvent = new ContactListCommStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
