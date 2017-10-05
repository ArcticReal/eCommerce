package com.skytala.eCommerce.domain.paymentApplication.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentApplication.event.PaymentApplicationAdded;
import com.skytala.eCommerce.domain.paymentApplication.mapper.PaymentApplicationMapper;
import com.skytala.eCommerce.domain.paymentApplication.model.PaymentApplication;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentApplication extends Command {

private PaymentApplication elementToBeAdded;
public AddPaymentApplication(PaymentApplication elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentApplication addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentApplicationId(delegator.getNextSeqId("PaymentApplication"));
GenericValue newValue = delegator.makeValue("PaymentApplication", elementToBeAdded.mapAttributeField());
addedElement = PaymentApplicationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentApplicationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
