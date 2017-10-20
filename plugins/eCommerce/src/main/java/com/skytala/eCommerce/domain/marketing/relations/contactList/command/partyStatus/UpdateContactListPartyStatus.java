package com.skytala.eCommerce.domain.marketing.relations.contactList.command.partyStatus;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus.ContactListPartyStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactListPartyStatus extends Command {

private ContactListPartyStatus elementToBeUpdated;

public UpdateContactListPartyStatus(ContactListPartyStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactListPartyStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactListPartyStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactListPartyStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactListPartyStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactListPartyStatus.class);
}
success = false;
}
Event resultingEvent = new ContactListPartyStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
