package com.skytala.eCommerce.domain.contactMechType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contactMechType.event.ContactMechTypeAdded;
import com.skytala.eCommerce.domain.contactMechType.mapper.ContactMechTypeMapper;
import com.skytala.eCommerce.domain.contactMechType.model.ContactMechType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMechType extends Command {

private ContactMechType elementToBeAdded;
public AddContactMechType(ContactMechType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMechType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContactMechTypeId(delegator.getNextSeqId("ContactMechType"));
GenericValue newValue = delegator.makeValue("ContactMechType", elementToBeAdded.mapAttributeField());
addedElement = ContactMechTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
