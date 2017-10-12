
package com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event.PayPalPaymentMethodFound;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.mapper.PayPalPaymentMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.model.PayPalPaymentMethod;


public class FindAllPayPalPaymentMethods extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PayPalPaymentMethod> returnVal = new ArrayList<PayPalPaymentMethod>();
try{
List<GenericValue> results = delegator.findAll("PayPalPaymentMethod", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PayPalPaymentMethodMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PayPalPaymentMethodFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
