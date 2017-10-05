package com.skytala.eCommerce.domain.budgetReview.query;
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
import com.skytala.eCommerce.domain.budgetReview.event.BudgetReviewAdded;
import com.skytala.eCommerce.domain.budgetReview.event.BudgetReviewFound;
import com.skytala.eCommerce.domain.budgetReview.mapper.BudgetReviewMapper;
import com.skytala.eCommerce.domain.budgetReview.model.BudgetReview;

public class FindBudgetReviewsBy extends Query {


Map<String, String> filter;
public FindBudgetReviewsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetReview> foundBudgetReviews = new ArrayList<BudgetReview>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("budgetReviewId")) { 
 GenericValue foundElement = delegator.findOne("BudgetReview", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(BudgetReview.class); 
 } 
}else { 
 buf = delegator.findAll("BudgetReview", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundBudgetReviews.add(BudgetReviewMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new BudgetReviewFound(foundBudgetReviews);
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
