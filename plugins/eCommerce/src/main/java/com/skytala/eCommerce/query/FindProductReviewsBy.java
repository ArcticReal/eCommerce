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
import com.skytala.eCommerce.entity.ProductReview;
import com.skytala.eCommerce.entity.ProductReviewMapper;
import com.skytala.eCommerce.event.ProductReviewFound;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class FindProductReviewsBy implements Query {


Map<String, String> filter;
public FindProductReviewsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public void execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductReview> foundProductReviews = new ArrayList<ProductReview>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productReviewId")) { 
 GenericValue foundElement = delegator.findOne("ProductReview", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductReview.class); 
 } 
}else { 
 buf = delegator.findAll("ProductReview", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductReviews.add(ProductReviewMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Broker.instance().publish(new ProductReviewFound(foundProductReviews));


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
