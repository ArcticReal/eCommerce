
package com.skytala.eCommerce.domain.product.relations.inventoryItem.query.tempRes;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.tempRes.InventoryItemTempResMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;


public class FindAllInventoryItemTempRess extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemTempRes> returnVal = new ArrayList<InventoryItemTempRes>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemTempRes", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemTempResMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemTempResFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
