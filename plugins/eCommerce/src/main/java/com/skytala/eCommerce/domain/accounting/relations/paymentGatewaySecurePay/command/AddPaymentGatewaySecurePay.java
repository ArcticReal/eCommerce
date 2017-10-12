package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.event.PaymentGatewaySecurePayAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.mapper.PaymentGatewaySecurePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.model.PaymentGatewaySecurePay;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewaySecurePay extends Command {

private PaymentGatewaySecurePay elementToBeAdded;
public AddPaymentGatewaySecurePay(PaymentGatewaySecurePay elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewaySecurePay addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewaySecurePay", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewaySecurePayMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewaySecurePayAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
