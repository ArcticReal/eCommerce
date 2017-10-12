package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.event.PaymentGatewayWorldPayAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.mapper.PaymentGatewayWorldPayMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayWorldPay.model.PaymentGatewayWorldPay;
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
