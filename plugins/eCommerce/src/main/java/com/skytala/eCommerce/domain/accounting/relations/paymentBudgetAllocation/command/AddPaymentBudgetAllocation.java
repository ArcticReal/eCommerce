package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.event.PaymentBudgetAllocationAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.mapper.PaymentBudgetAllocationMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model.PaymentBudgetAllocation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentBudgetAllocation extends Command {

private PaymentBudgetAllocation elementToBeAdded;
public AddPaymentBudgetAllocation(PaymentBudgetAllocation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentBudgetAllocation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetItemSeqId(delegator.getNextSeqId("PaymentBudgetAllocation"));
GenericValue newValue = delegator.makeValue("PaymentBudgetAllocation", elementToBeAdded.mapAttributeField());
addedElement = PaymentBudgetAllocationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentBudgetAllocationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
