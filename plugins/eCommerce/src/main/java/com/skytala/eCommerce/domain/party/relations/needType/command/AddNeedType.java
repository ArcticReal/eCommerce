package com.skytala.eCommerce.domain.party.relations.needType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.needType.event.NeedTypeAdded;
import com.skytala.eCommerce.domain.party.relations.needType.mapper.NeedTypeMapper;
import com.skytala.eCommerce.domain.party.relations.needType.model.NeedType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddNeedType extends Command {

private NeedType elementToBeAdded;
public AddNeedType(NeedType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

NeedType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setNeedTypeId(delegator.getNextSeqId("NeedType"));
GenericValue newValue = delegator.makeValue("NeedType", elementToBeAdded.mapAttributeField());
addedElement = NeedTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new NeedTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
