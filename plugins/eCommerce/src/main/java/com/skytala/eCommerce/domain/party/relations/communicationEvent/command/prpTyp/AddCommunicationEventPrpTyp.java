package com.skytala.eCommerce.domain.party.relations.communicationEvent.command.prpTyp;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp.CommunicationEventPrpTypAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.prpTyp.CommunicationEventPrpTypMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventPrpTyp extends Command {

private CommunicationEventPrpTyp elementToBeAdded;
public AddCommunicationEventPrpTyp(CommunicationEventPrpTyp elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventPrpTyp addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCommunicationEventPrpTypId(delegator.getNextSeqId("CommunicationEventPrpTyp"));
GenericValue newValue = delegator.makeValue("CommunicationEventPrpTyp", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventPrpTypMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventPrpTypAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
