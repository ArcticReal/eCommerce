package com.skytala.eCommerce.domain.accounting.relations.payment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.PaymentAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.PaymentMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.Payment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPayment extends Command {

private Payment elementToBeAdded;
public AddPayment(Payment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Payment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentId(delegator.getNextSeqId("Payment"));
GenericValue newValue = delegator.makeValue("Payment", elementToBeAdded.mapAttributeField());
addedElement = PaymentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
