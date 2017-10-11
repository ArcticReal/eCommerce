package com.skytala.eCommerce.domain.product.relations.supplierProduct.query;
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
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductAdded;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductFound;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.mapper.SupplierProductMapper;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.model.SupplierProduct;

public class FindSupplierProductsBy extends Query {


Map<String, String> filter;
public FindSupplierProductsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SupplierProduct> foundSupplierProducts = new ArrayList<SupplierProduct>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("supplierProductId")) { 
 GenericValue foundElement = delegator.findOne("SupplierProduct", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(SupplierProduct.class); 
 } 
}else { 
 buf = delegator.findAll("SupplierProduct", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundSupplierProducts.add(SupplierProductMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new SupplierProductFound(foundSupplierProducts);
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
