package com.skytala.eCommerce.domain.party.relations.contactMech.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute.ContactMechAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.attribute.ContactMechAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.attribute.ContactMechAttribute;
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
elementToBeAdded.setAttrName(delegator.getNextSeqId("ContactMechAttribute"));
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
