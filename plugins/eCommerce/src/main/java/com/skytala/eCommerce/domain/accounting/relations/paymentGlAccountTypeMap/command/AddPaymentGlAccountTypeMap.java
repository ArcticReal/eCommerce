package com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.event.PaymentGlAccountTypeMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.mapper.PaymentGlAccountTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.model.PaymentGlAccountTypeMap;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGlAccountTypeMap extends Command {

private PaymentGlAccountTypeMap elementToBeAdded;
public AddPaymentGlAccountTypeMap(PaymentGlAccountTypeMap elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGlAccountTypeMap addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGlAccountTypeMap", elementToBeAdded.mapAttributeField());
addedElement = PaymentGlAccountTypeMapMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGlAccountTypeMapAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
