package com.skytala.eCommerce.domain.billingAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.billingAccount.event.BillingAccountAdded;
import com.skytala.eCommerce.domain.billingAccount.mapper.BillingAccountMapper;
import com.skytala.eCommerce.domain.billingAccount.model.BillingAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBillingAccount extends Command {

private BillingAccount elementToBeAdded;
public AddBillingAccount(BillingAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BillingAccount addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBillingAccountId(delegator.getNextSeqId("BillingAccount"));
GenericValue newValue = delegator.makeValue("BillingAccount", elementToBeAdded.mapAttributeField());
addedElement = BillingAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BillingAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
