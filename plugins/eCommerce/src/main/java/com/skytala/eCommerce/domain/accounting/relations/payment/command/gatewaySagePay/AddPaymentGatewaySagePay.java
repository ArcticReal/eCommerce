package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewaySagePay;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay.PaymentGatewaySagePayAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewaySagePay.PaymentGatewaySagePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySagePay.PaymentGatewaySagePay;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewaySagePay extends Command {

private PaymentGatewaySagePay elementToBeAdded;
public AddPaymentGatewaySagePay(PaymentGatewaySagePay elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewaySagePay addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewaySagePay", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewaySagePayMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewaySagePayAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
