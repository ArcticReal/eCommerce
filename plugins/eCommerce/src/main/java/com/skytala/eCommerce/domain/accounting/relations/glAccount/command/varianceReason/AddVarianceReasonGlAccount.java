package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.varianceReason.VarianceReasonGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
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
