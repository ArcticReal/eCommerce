package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event.CommunicationEventWorkEffAdded;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.mapper.CommunicationEventWorkEffMapper;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventWorkEff extends Command {

private CommunicationEventWorkEff elementToBeAdded;
public AddCommunicationEventWorkEff(CommunicationEventWorkEff elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventWorkEff addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CommunicationEventWorkEff", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventWorkEffMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventWorkEffAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
