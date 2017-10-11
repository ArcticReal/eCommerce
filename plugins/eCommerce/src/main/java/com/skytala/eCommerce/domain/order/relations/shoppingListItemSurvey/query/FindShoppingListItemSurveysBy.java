package com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.query;
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
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.event.ShoppingListItemSurveyAdded;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.event.ShoppingListItemSurveyFound;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.mapper.ShoppingListItemSurveyMapper;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.model.ShoppingListItemSurvey;

public class FindShoppingListItemSurveysBy extends Query {


Map<String, String> filter;
public FindShoppingListItemSurveysBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShoppingListItemSurvey> foundShoppingListItemSurveys = new ArrayList<ShoppingListItemSurvey>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("shoppingListItemSurveyId")) { 
 GenericValue foundElement = delegator.findOne("ShoppingListItemSurvey", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ShoppingListItemSurvey.class); 
 } 
}else { 
 buf = delegator.findAll("ShoppingListItemSurvey", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundShoppingListItemSurveys.add(ShoppingListItemSurveyMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ShoppingListItemSurveyFound(foundShoppingListItemSurveys);
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
