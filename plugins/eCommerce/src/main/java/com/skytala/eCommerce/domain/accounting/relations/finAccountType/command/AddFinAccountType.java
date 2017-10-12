package com.skytala.eCommerce.domain.accounting.relations.finAccountType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.event.FinAccountTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.mapper.FinAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountType.model.FinAccountType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountType extends Command {

private FinAccountType elementToBeAdded;
public AddFinAccountType(FinAccountType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFinAccountTypeId(delegator.getNextSeqId("FinAccountType"));
GenericValue newValue = delegator.makeValue("FinAccountType", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
