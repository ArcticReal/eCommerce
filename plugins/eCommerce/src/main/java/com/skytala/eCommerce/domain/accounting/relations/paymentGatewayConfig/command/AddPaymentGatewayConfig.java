package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfig.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfig.event.PaymentGatewayConfigAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfig.mapper.PaymentGatewayConfigMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfig.model.PaymentGatewayConfig;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayConfig extends Command {

private PaymentGatewayConfig elementToBeAdded;
public AddPaymentGatewayConfig(PaymentGatewayConfig elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayConfig addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentGatewayConfigId(delegator.getNextSeqId("PaymentGatewayConfig"));
GenericValue newValue = delegator.makeValue("PaymentGatewayConfig", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayConfigMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayConfigAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
