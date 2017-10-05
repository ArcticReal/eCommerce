package com.skytala.eCommerce.domain.contactListType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contactListType.event.ContactListTypeAdded;
import com.skytala.eCommerce.domain.contactListType.mapper.ContactListTypeMapper;
import com.skytala.eCommerce.domain.contactListType.model.ContactListType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactListType extends Command {

private ContactListType elementToBeAdded;
public AddContactListType(ContactListType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactListType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContactListTypeId(delegator.getNextSeqId("ContactListType"));
GenericValue newValue = delegator.makeValue("ContactListType", elementToBeAdded.mapAttributeField());
addedElement = ContactListTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactListTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
