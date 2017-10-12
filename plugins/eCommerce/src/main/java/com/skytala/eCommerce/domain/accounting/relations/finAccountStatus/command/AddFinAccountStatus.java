package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.event.FinAccountStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.mapper.FinAccountStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountStatus extends Command {

private FinAccountStatus elementToBeAdded;
public AddFinAccountStatus(FinAccountStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountStatus", elementToBeAdded.mapAttributeField());
addedElement = FinAccountStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
