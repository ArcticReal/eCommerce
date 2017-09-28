package com.skytala.eCommerce.query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPromo;
import com.skytala.eCommerce.entity.ProductPromoMapper;
import com.skytala.eCommerce.event.ProductPromoFound;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class FindProductPromosBy implements Query {


Map<String, String> filter;
public FindProductPromosBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public void execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromo> foundProductPromos = new ArrayList<ProductPromo>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productPromoId")) { 
 GenericValue foundElement = delegator.findOne("ProductPromo", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductPromo.class); 
 } 
}else { 
 buf = delegator.findAll("ProductPromo", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductPromos.add(ProductPromoMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Broker.instance().publish(new ProductPromoFound(foundProductPromos));


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
}
