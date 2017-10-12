package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.event.PaymentGatewayAuthorizeNetAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.mapper.PaymentGatewayAuthorizeNetMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.model.PaymentGatewayAuthorizeNet;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayAuthorizeNet extends Command {

private PaymentGatewayAuthorizeNet elementToBeAdded;
public AddPaymentGatewayAuthorizeNet(PaymentGatewayAuthorizeNet elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayAuthorizeNet addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayAuthorizeNet", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayAuthorizeNetMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayAuthorizeNetAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
