package com.skytala.eCommerce.domain.contactMechPurposeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contactMechPurposeType.event.ContactMechPurposeTypeAdded;
import com.skytala.eCommerce.domain.contactMechPurposeType.mapper.ContactMechPurposeTypeMapper;
import com.skytala.eCommerce.domain.contactMechPurposeType.model.ContactMechPurposeType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContactMechPurposeType extends Command {

private ContactMechPurposeType elementToBeAdded;
public AddContactMechPurposeType(ContactMechPurposeType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContactMechPurposeType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContactMechPurposeTypeId(delegator.getNextSeqId("ContactMechPurposeType"));
GenericValue newValue = delegator.makeValue("ContactMechPurposeType", elementToBeAdded.mapAttributeField());
addedElement = ContactMechPurposeTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContactMechPurposeTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
