package com.skytala.eCommerce.domain.party.relations.termType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.termType.event.TermTypeAdded;
import com.skytala.eCommerce.domain.party.relations.termType.mapper.TermTypeMapper;
import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTermType extends Command {

private TermType elementToBeAdded;
public AddTermType(TermType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TermType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTermTypeId(delegator.getNextSeqId("TermType"));
GenericValue newValue = delegator.makeValue("TermType", elementToBeAdded.mapAttributeField());
addedElement = TermTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TermTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
