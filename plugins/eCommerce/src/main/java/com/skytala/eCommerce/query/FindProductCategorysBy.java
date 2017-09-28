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
import com.skytala.eCommerce.entity.ProductCategory;
import com.skytala.eCommerce.entity.ProductCategoryMapper;
import com.skytala.eCommerce.event.ProductCategoryFound;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class FindProductCategorysBy implements Query {


Map<String, String> filter;
public FindProductCategorysBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public void execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategory> foundProductCategorys = new ArrayList<ProductCategory>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productCategoryId")) { 
 GenericValue foundElement = delegator.findOne("ProductCategory", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductCategory.class); 
 } 
}else { 
 buf = delegator.findAll("ProductCategory", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductCategorys.add(ProductCategoryMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Broker.instance().publish(new ProductCategoryFound(foundProductCategorys));


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
