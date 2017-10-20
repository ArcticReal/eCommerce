package com.skytala.eCommerce.domain.accounting.relations.payment.command.method;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.method.PaymentMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.method.PaymentMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.method.PaymentMethod;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentMethod extends Command {

private PaymentMethod elementToBeAdded;
public AddPaymentMethod(PaymentMethod elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentMethod addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentMethodId(delegator.getNextSeqId("PaymentMethod"));
GenericValue newValue = delegator.makeValue("PaymentMethod", elementToBeAdded.mapAttributeField());
addedElement = PaymentMethodMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentMethodAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
