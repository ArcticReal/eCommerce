package com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.event.PaymentTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.mapper.PaymentTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.model.PaymentTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentTypeAttr extends Command {

private PaymentTypeAttr elementToBeAdded;
public AddPaymentTypeAttr(PaymentTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = PaymentTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}