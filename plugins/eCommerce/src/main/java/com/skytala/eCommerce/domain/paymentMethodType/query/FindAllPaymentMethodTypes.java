
package com.skytala.eCommerce.domain.paymentMethodType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.paymentMethodType.event.PaymentMethodTypeFound;
import com.skytala.eCommerce.domain.paymentMethodType.mapper.PaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.paymentMethodType.model.PaymentMethodType;


public class FindAllPaymentMethodTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentMethodType> returnVal = new ArrayList<PaymentMethodType>();
try{
List<GenericValue> results = delegator.findAll("PaymentMethodType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentMethodTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentMethodTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
