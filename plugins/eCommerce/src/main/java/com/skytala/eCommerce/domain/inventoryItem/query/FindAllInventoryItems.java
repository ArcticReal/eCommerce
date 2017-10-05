
package com.skytala.eCommerce.domain.inventoryItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.inventoryItem.event.InventoryItemFound;
import com.skytala.eCommerce.domain.inventoryItem.mapper.InventoryItemMapper;
import com.skytala.eCommerce.domain.inventoryItem.model.InventoryItem;


public class FindAllInventoryItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItem> returnVal = new ArrayList<InventoryItem>();
try{
List<GenericValue> results = delegator.findAll("InventoryItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
