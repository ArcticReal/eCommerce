package com.skytala.eCommerce.domain.marketing.relations.contactList.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.ContactListAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.ContactListMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.ContactList;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactList extends Command {

private ContactList elementToBeAdded;
public AddContactList(ContactList elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactList addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContactListId(delegator.getNextSeqId("ContactList"));
GenericValue newValue = delegator.makeValue("ContactList", elementToBeAdded.mapAttributeField());
addedElement = ContactListMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactListAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
