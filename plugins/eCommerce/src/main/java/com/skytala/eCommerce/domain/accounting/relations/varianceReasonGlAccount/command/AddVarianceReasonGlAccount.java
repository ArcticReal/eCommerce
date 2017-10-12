package com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.event.VarianceReasonGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.mapper.VarianceReasonGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.model.VarianceReasonGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddVarianceReasonGlAccount extends Command {

private VarianceReasonGlAccount elementToBeAdded;
public AddVarianceReasonGlAccount(VarianceReasonGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

VarianceReasonGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("VarianceReasonGlAccount", elementToBeAdded.mapAttributeField());
addedElement = VarianceReasonGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new VarianceReasonGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
