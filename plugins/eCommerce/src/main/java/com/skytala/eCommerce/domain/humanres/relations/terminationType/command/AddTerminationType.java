package com.skytala.eCommerce.domain.humanres.relations.terminationType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.mapper.TerminationTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.model.TerminationType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTerminationType extends Command {

private TerminationType elementToBeAdded;
public AddTerminationType(TerminationType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TerminationType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTerminationTypeId(delegator.getNextSeqId("TerminationType"));
GenericValue newValue = delegator.makeValue("TerminationType", elementToBeAdded.mapAttributeField());
addedElement = TerminationTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TerminationTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
