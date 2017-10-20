
package com.skytala.eCommerce.domain.product.relations.inventoryItem.query.status;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.status.InventoryItemStatusMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status.InventoryItemStatus;


public class FindAllInventoryItemStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemStatus> returnVal = new ArrayList<InventoryItemStatus>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
