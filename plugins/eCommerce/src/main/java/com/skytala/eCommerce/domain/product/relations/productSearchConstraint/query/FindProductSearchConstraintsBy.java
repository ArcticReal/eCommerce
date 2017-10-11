package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.query;
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
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event.ProductSearchConstraintAdded;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event.ProductSearchConstraintFound;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.mapper.ProductSearchConstraintMapper;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;

public class FindProductSearchConstraintsBy extends Query {


Map<String, String> filter;
public FindProductSearchConstraintsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductSearchConstraint> foundProductSearchConstraints = new ArrayList<ProductSearchConstraint>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productSearchConstraintId")) { 
 GenericValue foundElement = delegator.findOne("ProductSearchConstraint", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductSearchConstraint.class); 
 } 
}else { 
 buf = delegator.findAll("ProductSearchConstraint", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductSearchConstraints.add(ProductSearchConstraintMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ProductSearchConstraintFound(foundProductSearchConstraints);
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
