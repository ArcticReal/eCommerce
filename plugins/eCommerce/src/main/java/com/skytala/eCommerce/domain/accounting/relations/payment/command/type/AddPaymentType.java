package com.skytala.eCommerce.domain.accounting.relations.payment.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.type.PaymentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentType extends Command {

private PaymentType elementToBeAdded;
public AddPaymentType(PaymentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentTypeId(delegator.getNextSeqId("PaymentType"));
GenericValue newValue = delegator.makeValue("PaymentType", elementToBeAdded.mapAttributeField());
addedElement = PaymentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
