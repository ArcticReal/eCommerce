package com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.query;
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
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.event.DataResourceTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.event.DataResourceTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.mapper.DataResourceTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.model.DataResourceTypeAttr;

public class FindDataResourceTypeAttrsBy extends Query {


Map<String, String> filter;
public FindDataResourceTypeAttrsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceTypeAttr> foundDataResourceTypeAttrs = new ArrayList<DataResourceTypeAttr>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("dataResourceTypeAttrId")) { 
 GenericValue foundElement = delegator.findOne("DataResourceTypeAttr", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(DataResourceTypeAttr.class); 
 } 
}else { 
 buf = delegator.findAll("DataResourceTypeAttr", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundDataResourceTypeAttrs.add(DataResourceTypeAttrMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new DataResourceTypeAttrFound(foundDataResourceTypeAttrs);
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
