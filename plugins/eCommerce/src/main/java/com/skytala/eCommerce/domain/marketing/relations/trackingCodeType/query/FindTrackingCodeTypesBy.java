package com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.query;
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
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.event.TrackingCodeTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.event.TrackingCodeTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.mapper.TrackingCodeTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.trackingCodeType.model.TrackingCodeType;

public class FindTrackingCodeTypesBy extends Query {


Map<String, String> filter;
public FindTrackingCodeTypesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TrackingCodeType> foundTrackingCodeTypes = new ArrayList<TrackingCodeType>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("trackingCodeTypeId")) { 
 GenericValue foundElement = delegator.findOne("TrackingCodeType", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(TrackingCodeType.class); 
 } 
}else { 
 buf = delegator.findAll("TrackingCodeType", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundTrackingCodeTypes.add(TrackingCodeTypeMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new TrackingCodeTypeFound(foundTrackingCodeTypes);
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
