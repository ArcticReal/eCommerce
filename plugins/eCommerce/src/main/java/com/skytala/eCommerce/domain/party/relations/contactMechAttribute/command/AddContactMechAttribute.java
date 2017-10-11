package com.skytala.eCommerce.domain.party.relations.contactMechAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.event.ContactMechAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.mapper.ContactMechAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.model.ContactMechAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMechAttribute extends Command {

private ContactMechAttribute elementToBeAdded;
public AddContactMechAttribute(ContactMechAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMechAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContactMechAttribute", elementToBeAdded.mapAttributeField());
addedElement = ContactMechAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
