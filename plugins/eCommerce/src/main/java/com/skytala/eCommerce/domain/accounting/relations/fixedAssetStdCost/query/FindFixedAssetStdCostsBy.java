package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.query;
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
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event.FixedAssetStdCostFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.mapper.FixedAssetStdCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;

public class FindFixedAssetStdCostsBy extends Query {


Map<String, String> filter;
public FindFixedAssetStdCostsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetStdCost> foundFixedAssetStdCosts = new ArrayList<FixedAssetStdCost>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("fixedAssetStdCostId")) { 
 GenericValue foundElement = delegator.findOne("FixedAssetStdCost", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(FixedAssetStdCost.class); 
 } 
}else { 
 buf = delegator.findAll("FixedAssetStdCost", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundFixedAssetStdCosts.add(FixedAssetStdCostMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new FixedAssetStdCostFound(foundFixedAssetStdCosts);
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
