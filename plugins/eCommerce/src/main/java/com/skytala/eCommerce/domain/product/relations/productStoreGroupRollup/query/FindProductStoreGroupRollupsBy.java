package com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.query;
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
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.event.ProductStoreGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.event.ProductStoreGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.mapper.ProductStoreGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.model.ProductStoreGroupRollup;

public class FindProductStoreGroupRollupsBy extends Query {


Map<String, String> filter;
public FindProductStoreGroupRollupsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroupRollup> foundProductStoreGroupRollups = new ArrayList<ProductStoreGroupRollup>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productStoreGroupRollupId")) { 
 GenericValue foundElement = delegator.findOne("ProductStoreGroupRollup", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductStoreGroupRollup.class); 
 } 
}else { 
 buf = delegator.findAll("ProductStoreGroupRollup", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductStoreGroupRollups.add(ProductStoreGroupRollupMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ProductStoreGroupRollupFound(foundProductStoreGroupRollups);
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