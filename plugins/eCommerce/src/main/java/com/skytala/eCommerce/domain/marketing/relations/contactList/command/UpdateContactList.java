package com.skytala.eCommerce.domain.marketing.relations.contactList.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.ContactListUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.ContactList;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactList extends Command {

private ContactList elementToBeUpdated;

public UpdateContactList(ContactList elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactList getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactList elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactList", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactList.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactList.class);
}
success = false;
}
Event resultingEvent = new ContactListUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
