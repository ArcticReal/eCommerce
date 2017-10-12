package com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.event.ContactListCommStatusAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.mapper.ContactListCommStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.model.ContactListCommStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactListCommStatus extends Command {

private ContactListCommStatus elementToBeAdded;
public AddContactListCommStatus(ContactListCommStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactListCommStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContactListCommStatus", elementToBeAdded.mapAttributeField());
addedElement = ContactListCommStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactListCommStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
