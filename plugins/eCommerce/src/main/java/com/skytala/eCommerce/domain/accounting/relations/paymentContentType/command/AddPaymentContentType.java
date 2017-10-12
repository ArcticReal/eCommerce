package com.skytala.eCommerce.domain.accounting.relations.paymentContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentContentType.event.PaymentContentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentContentType.mapper.PaymentContentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentContentType.model.PaymentContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentContentType extends Command {

private PaymentContentType elementToBeAdded;
public AddPaymentContentType(PaymentContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentContentTypeId(delegator.getNextSeqId("PaymentContentType"));
GenericValue newValue = delegator.makeValue("PaymentContentType", elementToBeAdded.mapAttributeField());
addedElement = PaymentContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
