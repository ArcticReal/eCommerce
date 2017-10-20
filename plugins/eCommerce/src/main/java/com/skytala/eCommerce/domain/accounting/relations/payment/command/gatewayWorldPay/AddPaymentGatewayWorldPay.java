package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayWorldPay;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay.PaymentGatewayWorldPayAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayWorldPay.PaymentGatewayWorldPayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayWorldPay extends Command {

private PaymentGatewayWorldPay elementToBeAdded;
public AddPaymentGatewayWorldPay(PaymentGatewayWorldPay elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayWorldPay addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayWorldPay", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayWorldPayMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayWorldPayAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
