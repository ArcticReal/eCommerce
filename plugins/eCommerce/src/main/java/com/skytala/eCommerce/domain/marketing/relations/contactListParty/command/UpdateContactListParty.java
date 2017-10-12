package com.skytala.eCommerce.domain.marketing.relations.contactListParty.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.event.ContactListPartyUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactListParty extends Command {

private ContactListParty elementToBeUpdated;

public UpdateContactListParty(ContactListParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactListParty getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactListParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactListParty", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactListParty.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactListParty.class);
}
success = false;
}
Event resultingEvent = new ContactListPartyUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
