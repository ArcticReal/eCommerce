package com.skytala.eCommerce.domain.metaDataPredicate.query;
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
import com.skytala.eCommerce.domain.metaDataPredicate.event.MetaDataPredicateAdded;
import com.skytala.eCommerce.domain.metaDataPredicate.event.MetaDataPredicateFound;
import com.skytala.eCommerce.domain.metaDataPredicate.mapper.MetaDataPredicateMapper;
import com.skytala.eCommerce.domain.metaDataPredicate.model.MetaDataPredicate;

public class FindMetaDataPredicatesBy extends Query {


Map<String, String> filter;
public FindMetaDataPredicatesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MetaDataPredicate> foundMetaDataPredicates = new ArrayList<MetaDataPredicate>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("metaDataPredicateId")) { 
 GenericValue foundElement = delegator.findOne("MetaDataPredicate", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(MetaDataPredicate.class); 
 } 
}else { 
 buf = delegator.findAll("MetaDataPredicate", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundMetaDataPredicates.add(MetaDataPredicateMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new MetaDataPredicateFound(foundMetaDataPredicates);
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
