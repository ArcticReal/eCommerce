
package com.skytala.eCommerce.domain.payment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.payment.event.PaymentFound;
import com.skytala.eCommerce.domain.payment.mapper.PaymentMapper;
import com.skytala.eCommerce.domain.payment.model.Payment;


public class FindAllPayments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Payment> returnVal = new ArrayList<Payment>();
try{
List<GenericValue> results = delegator.findAll("Payment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
