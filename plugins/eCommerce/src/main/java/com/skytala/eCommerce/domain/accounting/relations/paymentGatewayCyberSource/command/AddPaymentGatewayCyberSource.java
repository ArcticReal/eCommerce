package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.event.PaymentGatewayCyberSourceAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.mapper.PaymentGatewayCyberSourceMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.model.PaymentGatewayCyberSource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayCyberSource extends Command {

private PaymentGatewayCyberSource elementToBeAdded;
public AddPaymentGatewayCyberSource(PaymentGatewayCyberSource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayCyberSource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayCyberSource", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayCyberSourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayCyberSourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
