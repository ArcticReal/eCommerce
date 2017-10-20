package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeGlAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount.FinAccountTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.typeGlAccount.FinAccountTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeGlAccount.FinAccountTypeGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountTypeGlAccount extends Command {

private FinAccountTypeGlAccount elementToBeAdded;
public AddFinAccountTypeGlAccount(FinAccountTypeGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountTypeGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountTypeGlAccount", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTypeGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTypeGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
