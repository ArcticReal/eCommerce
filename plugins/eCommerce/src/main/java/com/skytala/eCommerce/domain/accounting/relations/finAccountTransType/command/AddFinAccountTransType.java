package com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.event.FinAccountTransTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.mapper.FinAccountTransTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransType.model.FinAccountTransType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountTransType extends Command {

private FinAccountTransType elementToBeAdded;
public AddFinAccountTransType(FinAccountTransType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountTransType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFinAccountTransTypeId(delegator.getNextSeqId("FinAccountTransType"));
GenericValue newValue = delegator.makeValue("FinAccountTransType", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTransTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTransTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
