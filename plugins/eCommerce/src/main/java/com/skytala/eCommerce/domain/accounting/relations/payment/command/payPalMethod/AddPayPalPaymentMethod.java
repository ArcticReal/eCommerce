package com.skytala.eCommerce.domain.accounting.relations.payment.command.payPalMethod;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod.PayPalPaymentMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.payPalMethod.PayPalPaymentMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.payPalMethod.PayPalPaymentMethod;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPayPalPaymentMethod extends Command {

private PayPalPaymentMethod elementToBeAdded;
public AddPayPalPaymentMethod(PayPalPaymentMethod elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PayPalPaymentMethod addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PayPalPaymentMethod", elementToBeAdded.mapAttributeField());
addedElement = PayPalPaymentMethodMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PayPalPaymentMethodAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
