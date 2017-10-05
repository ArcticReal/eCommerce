package com.skytala.eCommerce.domain.desiredFeature.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.desiredFeature.event.DesiredFeatureAdded;
import com.skytala.eCommerce.domain.desiredFeature.mapper.DesiredFeatureMapper;
import com.skytala.eCommerce.domain.desiredFeature.model.DesiredFeature;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDesiredFeature extends Command {

private DesiredFeature elementToBeAdded;
public AddDesiredFeature(DesiredFeature elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DesiredFeature addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDesiredFeatureId(delegator.getNextSeqId("DesiredFeature"));
GenericValue newValue = delegator.makeValue("DesiredFeature", elementToBeAdded.mapAttributeField());
addedElement = DesiredFeatureMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DesiredFeatureAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
