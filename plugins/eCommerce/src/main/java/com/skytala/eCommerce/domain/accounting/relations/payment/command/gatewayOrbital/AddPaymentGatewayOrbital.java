package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayOrbital;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital.PaymentGatewayOrbitalAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayOrbital.PaymentGatewayOrbitalMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayOrbital.PaymentGatewayOrbital;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayOrbital extends Command {

private PaymentGatewayOrbital elementToBeAdded;
public AddPaymentGatewayOrbital(PaymentGatewayOrbital elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayOrbital addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayOrbital", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayOrbitalMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayOrbitalAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
