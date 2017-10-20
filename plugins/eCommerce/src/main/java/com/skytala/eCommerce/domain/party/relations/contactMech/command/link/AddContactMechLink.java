package com.skytala.eCommerce.domain.party.relations.contactMech.command.link;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.link.ContactMechLinkAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.link.ContactMechLinkMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.link.ContactMechLink;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMechLink extends Command {

private ContactMechLink elementToBeAdded;
public AddContactMechLink(ContactMechLink elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMechLink addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContactMechLink", elementToBeAdded.mapAttributeField());
addedElement = ContactMechLinkMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechLinkAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
