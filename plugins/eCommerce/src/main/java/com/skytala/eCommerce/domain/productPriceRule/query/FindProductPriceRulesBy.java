package com.skytala.eCommerce.domain.productPriceRule.query;
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
import com.skytala.eCommerce.domain.productPriceRule.event.ProductPriceRuleAdded;
import com.skytala.eCommerce.domain.productPriceRule.event.ProductPriceRuleFound;
import com.skytala.eCommerce.domain.productPriceRule.mapper.ProductPriceRuleMapper;
import com.skytala.eCommerce.domain.productPriceRule.model.ProductPriceRule;

public class FindProductPriceRulesBy extends Query {


Map<String, String> filter;
public FindProductPriceRulesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceRule> foundProductPriceRules = new ArrayList<ProductPriceRule>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("productPriceRuleId")) { 
 GenericValue foundElement = delegator.findOne("ProductPriceRule", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ProductPriceRule.class); 
 } 
}else { 
 buf = delegator.findAll("ProductPriceRule", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundProductPriceRules.add(ProductPriceRuleMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ProductPriceRuleFound(foundProductPriceRules);
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
