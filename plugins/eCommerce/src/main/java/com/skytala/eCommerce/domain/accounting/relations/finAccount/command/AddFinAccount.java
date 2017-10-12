package com.skytala.eCommerce.domain.accounting.relations.finAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.FinAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccount extends Command {

private FinAccount elementToBeAdded;
public AddFinAccount(FinAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccount addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFinAccountId(delegator.getNextSeqId("FinAccount"));
GenericValue newValue = delegator.makeValue("FinAccount", elementToBeAdded.mapAttributeField());
addedElement = FinAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
