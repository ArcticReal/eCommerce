
package com.skytala.eCommerce.domain.orderPaymentPreference.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.orderPaymentPreference.event.OrderPaymentPreferenceFound;
import com.skytala.eCommerce.domain.orderPaymentPreference.mapper.OrderPaymentPreferenceMapper;
import com.skytala.eCommerce.domain.orderPaymentPreference.model.OrderPaymentPreference;


public class FindAllOrderPaymentPreferences extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderPaymentPreference> returnVal = new ArrayList<OrderPaymentPreference>();
try{
List<GenericValue> results = delegator.findAll("OrderPaymentPreference", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderPaymentPreferenceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderPaymentPreferenceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
