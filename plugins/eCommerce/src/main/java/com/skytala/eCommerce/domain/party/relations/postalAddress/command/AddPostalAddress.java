package com.skytala.eCommerce.domain.party.relations.postalAddress.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.PostalAddressAdded;
import com.skytala.eCommerce.domain.party.relations.postalAddress.mapper.PostalAddressMapper;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPostalAddress extends Command {

private PostalAddress elementToBeAdded;
public AddPostalAddress(PostalAddress elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PostalAddress addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PostalAddress", elementToBeAdded.mapAttributeField());
addedElement = PostalAddressMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PostalAddressAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
