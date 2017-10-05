
package com.skytala.eCommerce.domain.paymentMethod.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.paymentMethod.event.PaymentMethodFound;
import com.skytala.eCommerce.domain.paymentMethod.mapper.PaymentMethodMapper;
import com.skytala.eCommerce.domain.paymentMethod.model.PaymentMethod;


public class FindAllPaymentMethods extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentMethod> returnVal = new ArrayList<PaymentMethod>();
try{
List<GenericValue> results = delegator.findAll("PaymentMethod", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentMethodMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentMethodFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
