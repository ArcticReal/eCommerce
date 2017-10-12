package com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.event.FinAccountAuthAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.mapper.FinAccountAuthMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.model.FinAccountAuth;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountAuth extends Command {

private FinAccountAuth elementToBeAdded;
public AddFinAccountAuth(FinAccountAuth elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountAuth addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFinAccountAuthId(delegator.getNextSeqId("FinAccountAuth"));
GenericValue newValue = delegator.makeValue("FinAccountAuth", elementToBeAdded.mapAttributeField());
addedElement = FinAccountAuthMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountAuthAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
