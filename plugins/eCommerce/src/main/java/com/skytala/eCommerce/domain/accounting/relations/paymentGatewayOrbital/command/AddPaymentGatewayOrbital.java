package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.mapper.PaymentGatewayOrbitalMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;
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
