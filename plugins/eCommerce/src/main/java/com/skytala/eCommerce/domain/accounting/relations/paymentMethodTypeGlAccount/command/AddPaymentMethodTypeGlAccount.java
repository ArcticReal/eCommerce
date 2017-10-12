package com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.event.PaymentMethodTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.mapper.PaymentMethodTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.model.PaymentMethodTypeGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentMethodTypeGlAccount extends Command {

private PaymentMethodTypeGlAccount elementToBeAdded;
public AddPaymentMethodTypeGlAccount(PaymentMethodTypeGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentMethodTypeGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentMethodTypeGlAccount", elementToBeAdded.mapAttributeField());
addedElement = PaymentMethodTypeGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentMethodTypeGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
