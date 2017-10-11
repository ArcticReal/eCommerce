package com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.event.ContactMechPurposeTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMechPurposeType.model.ContactMechPurposeType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactMechPurposeType extends Command {

private ContactMechPurposeType elementToBeUpdated;

public UpdateContactMechPurposeType(ContactMechPurposeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactMechPurposeType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactMechPurposeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactMechPurposeType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactMechPurposeType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactMechPurposeType.class);
}
success = false;
}
Event resultingEvent = new ContactMechPurposeTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
