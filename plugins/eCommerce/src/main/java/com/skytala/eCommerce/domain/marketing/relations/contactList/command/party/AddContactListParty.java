package com.skytala.eCommerce.domain.marketing.relations.contactList.command.party;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.party.ContactListPartyAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.party.ContactListPartyMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.party.ContactListParty;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactListParty extends Command {

private ContactListParty elementToBeAdded;
public AddContactListParty(ContactListParty elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactListParty addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContactListParty", elementToBeAdded.mapAttributeField());
addedElement = ContactListPartyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactListPartyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
