package com.skytala.eCommerce.domain.product.relations.subscriptionActivity.query;
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
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event.SubscriptionActivityAdded;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event.SubscriptionActivityFound;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.mapper.SubscriptionActivityMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.model.SubscriptionActivity;

public class FindSubscriptionActivitysBy extends Query {


Map<String, String> filter;
public FindSubscriptionActivitysBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionActivity> foundSubscriptionActivitys = new ArrayList<SubscriptionActivity>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("subscriptionActivityId")) { 
 GenericValue foundElement = delegator.findOne("SubscriptionActivity", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(SubscriptionActivity.class); 
 } 
}else { 
 buf = delegator.findAll("SubscriptionActivity", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundSubscriptionActivitys.add(SubscriptionActivityMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new SubscriptionActivityFound(foundSubscriptionActivitys);
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
