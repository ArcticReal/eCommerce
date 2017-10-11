package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContactMechTypeAttr extends Command {

private ContactMechTypeAttr elementToBeUpdated;

public UpdateContactMechTypeAttr(ContactMechTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContactMechTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContactMechTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContactMechTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContactMechTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContactMechTypeAttr.class);
}
success = false;
}
Event resultingEvent = new ContactMechTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
