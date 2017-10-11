package com.skytala.eCommerce.domain.party.relations.addendum.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.addendum.event.AddendumAdded;
import com.skytala.eCommerce.domain.party.relations.addendum.mapper.AddendumMapper;
import com.skytala.eCommerce.domain.party.relations.addendum.model.Addendum;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAddendum extends Command {

private Addendum elementToBeAdded;
public AddAddendum(Addendum elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Addendum addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAddendumId(delegator.getNextSeqId("Addendum"));
GenericValue newValue = delegator.makeValue("Addendum", elementToBeAdded.mapAttributeField());
addedElement = AddendumMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AddendumAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
