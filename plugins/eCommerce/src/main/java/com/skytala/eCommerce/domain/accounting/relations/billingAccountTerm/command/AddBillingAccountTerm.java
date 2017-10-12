package com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.event.BillingAccountTermAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.mapper.BillingAccountTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTerm.model.BillingAccountTerm;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBillingAccountTerm extends Command {

private BillingAccountTerm elementToBeAdded;
public AddBillingAccountTerm(BillingAccountTerm elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BillingAccountTerm addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBillingAccountTermId(delegator.getNextSeqId("BillingAccountTerm"));
GenericValue newValue = delegator.makeValue("BillingAccountTerm", elementToBeAdded.mapAttributeField());
addedElement = BillingAccountTermMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BillingAccountTermAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
