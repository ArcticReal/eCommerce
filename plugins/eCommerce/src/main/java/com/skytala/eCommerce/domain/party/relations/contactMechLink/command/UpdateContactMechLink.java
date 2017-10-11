package com.skytala.eCommerce.domain.party.relations.contactMechLink.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.contactMechLink.event.ContactMechLinkUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMechLink.model.ContactMechLink;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactMechLink extends Command {

private ContactMechLink elementToBeUpdated;

public UpdateContactMechLink(ContactMechLink elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactMechLink getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactMechLink elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactMechLink", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactMechLink.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactMechLink.class);
}
success = false;
}
Event resultingEvent = new ContactMechLinkUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
