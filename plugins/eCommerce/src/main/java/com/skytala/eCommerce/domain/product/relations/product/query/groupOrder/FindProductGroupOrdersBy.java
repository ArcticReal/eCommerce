package com.skytala.eCommerce.domain.product.relations.product.query.groupOrder;
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
import com.skytala.eCommerce.domain.product.relations.product.event.groupOrder.ProductGroupOrderFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.groupOrder.ProductGroupOrderMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;

public class FindProductGroupOrdersBy extends Query {


Map<String, String> filter;
public FindProductGroupOrdersBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductGroupOrder> foundProductGroupOrders = new ArrayList<ProductGroupOrder>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productGroupOrderId")) { 
 GenericValue foundElement = delegator.findOne("ProductGroupOrder", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductGroupOrder.class); 
 } 
}else { 
 buf = delegator.findAll("ProductGroupOrder", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductGroupOrders.add(ProductGroupOrderMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ProductGroupOrderFound(foundProductGroupOrders);
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
