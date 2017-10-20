package com.skytala.eCommerce.domain.party.relations.postalAddress.command.boundary;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary.PostalAddressBoundaryAdded;
import com.skytala.eCommerce.domain.party.relations.postalAddress.mapper.boundary.PostalAddressBoundaryMapper;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.boundary.PostalAddressBoundary;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPostalAddressBoundary extends Command {

private PostalAddressBoundary elementToBeAdded;
public AddPostalAddressBoundary(PostalAddressBoundary elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PostalAddressBoundary addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PostalAddressBoundary", elementToBeAdded.mapAttributeField());
addedElement = PostalAddressBoundaryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PostalAddressBoundaryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
