package com.skytala.eCommerce.domain.content.relations.otherDataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.otherDataResource.event.OtherDataResourceAdded;
import com.skytala.eCommerce.domain.content.relations.otherDataResource.mapper.OtherDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.otherDataResource.model.OtherDataResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOtherDataResource extends Command {

private OtherDataResource elementToBeAdded;
public AddOtherDataResource(OtherDataResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OtherDataResource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OtherDataResource", elementToBeAdded.mapAttributeField());
addedElement = OtherDataResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OtherDataResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
