package com.skytala.eCommerce.domain.orderPaymentPreference.query;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.orderPaymentPreference.event.OrderPaymentPreferenceAdded;
import com.skytala.eCommerce.domain.orderPaymentPreference.event.OrderPaymentPreferenceFound;
import com.skytala.eCommerce.domain.orderPaymentPreference.mapper.OrderPaymentPreferenceMapper;
import com.skytala.eCommerce.domain.orderPaymentPreference.model.OrderPaymentPreference;

public class FindOrderPaymentPreferencesBy extends Query {


Map<String, String> filter;
public FindOrderPaymentPreferencesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderPaymentPreference> foundOrderPaymentPreferences = new ArrayList<OrderPaymentPreference>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("orderPaymentPreferenceId")) { 
 GenericValue foundElement = delegator.findOne("OrderPaymentPreference", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(OrderPaymentPreference.class); 
 } 
}else { 
 buf = delegator.findAll("OrderPaymentPreference", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundOrderPaymentPreferences.add(OrderPaymentPreferenceMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new OrderPaymentPreferenceFound(foundOrderPaymentPreferences);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
public void setFilter(Map<String, String> newFilter) {
this.filter = newFilter;
}
}
