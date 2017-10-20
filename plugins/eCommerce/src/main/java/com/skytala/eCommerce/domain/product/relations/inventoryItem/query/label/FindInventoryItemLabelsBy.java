package com.skytala.eCommerce.domain.product.relations.inventoryItem.query.label;
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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.label.InventoryItemLabelMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label.InventoryItemLabel;

public class FindInventoryItemLabelsBy extends Query {


Map<String, String> filter;
public FindInventoryItemLabelsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemLabel> foundInventoryItemLabels = new ArrayList<InventoryItemLabel>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("inventoryItemLabelId")) { 
 GenericValue foundElement = delegator.findOne("InventoryItemLabel", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(InventoryItemLabel.class); 
 } 
}else { 
 buf = delegator.findAll("InventoryItemLabel", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundInventoryItemLabels.add(InventoryItemLabelMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new InventoryItemLabelFound(foundInventoryItemLabels);
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
