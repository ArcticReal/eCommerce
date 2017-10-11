package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.mapper.ContactMechTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMechTypeAttr extends Command {

private ContactMechTypeAttr elementToBeAdded;
public AddContactMechTypeAttr(ContactMechTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMechTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContactMechTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = ContactMechTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
