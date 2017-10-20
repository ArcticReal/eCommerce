package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayPal;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayPal.PaymentGatewayPayPalAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayPal.PaymentGatewayPayPalMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayPal.PaymentGatewayPayPal;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayPayPal extends Command {

private PaymentGatewayPayPal elementToBeAdded;
public AddPaymentGatewayPayPal(PaymentGatewayPayPal elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayPayPal addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayPayPal", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayPayPalMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayPayPalAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
