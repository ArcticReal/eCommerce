package com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.query;
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
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.event.ProductStoreKeywordOvrdAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.event.ProductStoreKeywordOvrdFound;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.mapper.ProductStoreKeywordOvrdMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.model.ProductStoreKeywordOvrd;

public class FindProductStoreKeywordOvrdsBy extends Query {


Map<String, String> filter;
public FindProductStoreKeywordOvrdsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreKeywordOvrd> foundProductStoreKeywordOvrds = new ArrayList<ProductStoreKeywordOvrd>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productStoreKeywordOvrdId")) { 
 GenericValue foundElement = delegator.findOne("ProductStoreKeywordOvrd", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductStoreKeywordOvrd.class); 
 } 
}else { 
 buf = delegator.findAll("ProductStoreKeywordOvrd", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductStoreKeywordOvrds.add(ProductStoreKeywordOvrdMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ProductStoreKeywordOvrdFound(foundProductStoreKeywordOvrds);
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
