
package com.skytala.eCommerce.domain.inventoryItemType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.inventoryItemType.event.InventoryItemTypeFound;
import com.skytala.eCommerce.domain.inventoryItemType.mapper.InventoryItemTypeMapper;
import com.skytala.eCommerce.domain.inventoryItemType.model.InventoryItemType;


public class FindAllInventoryItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemType> returnVal = new ArrayList<InventoryItemType>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}