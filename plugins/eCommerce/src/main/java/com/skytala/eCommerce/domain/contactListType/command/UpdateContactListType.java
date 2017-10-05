package com.skytala.eCommerce.domain.contactListType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.contactListType.event.ContactListTypeUpdated;
import com.skytala.eCommerce.domain.contactListType.model.ContactListType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactListType extends Command {

private ContactListType elementToBeUpdated;

public UpdateContactListType(ContactListType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactListType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactListType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactListType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactListType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactListType.class);
}
success = false;
}
Event resultingEvent = new ContactListTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
