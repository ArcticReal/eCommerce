package com.skytala.eCommerce.domain.marketing.relations.trackingCode.query.orderReturn;
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
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.orderReturn.TrackingCodeOrderReturnFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.mapper.orderReturn.TrackingCodeOrderReturnMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.orderReturn.TrackingCodeOrderReturn;

public class FindTrackingCodeOrderReturnsBy extends Query {


Map<String, String> filter;
public FindTrackingCodeOrderReturnsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCodeOrderReturn> foundTrackingCodeOrderReturns = new ArrayList<TrackingCodeOrderReturn>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("trackingCodeOrderReturnId")) { 
 GenericValue foundElement = delegator.findOne("TrackingCodeOrderReturn", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(TrackingCodeOrderReturn.class); 
 } 
}else { 
 buf = delegator.findAll("TrackingCodeOrderReturn", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundTrackingCodeOrderReturns.add(TrackingCodeOrderReturnMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new TrackingCodeOrderReturnFound(foundTrackingCodeOrderReturns);
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
