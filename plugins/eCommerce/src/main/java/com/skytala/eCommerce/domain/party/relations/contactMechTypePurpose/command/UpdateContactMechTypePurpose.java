package com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.event.ContactMechTypePurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.model.ContactMechTypePurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactMechTypePurpose extends Command {

private ContactMechTypePurpose elementToBeUpdated;

public UpdateContactMechTypePurpose(ContactMechTypePurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactMechTypePurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactMechTypePurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactMechTypePurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactMechTypePurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactMechTypePurpose.class);
}
success = false;
}
Event resultingEvent = new ContactMechTypePurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
