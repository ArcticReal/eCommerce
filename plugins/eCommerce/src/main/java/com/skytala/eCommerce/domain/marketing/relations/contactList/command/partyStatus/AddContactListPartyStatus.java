package com.skytala.eCommerce.domain.marketing.relations.contactList.command.partyStatus;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.partyStatus.ContactListPartyStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus.ContactListPartyStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactListPartyStatus extends Command {

private ContactListPartyStatus elementToBeAdded;
public AddContactListPartyStatus(ContactListPartyStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactListPartyStatus addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyId(delegator.getNextSeqId("ContactListPartyStatus"));
GenericValue newValue = delegator.makeValue("ContactListPartyStatus", elementToBeAdded.mapAttributeField());
addedElement = ContactListPartyStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactListPartyStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
