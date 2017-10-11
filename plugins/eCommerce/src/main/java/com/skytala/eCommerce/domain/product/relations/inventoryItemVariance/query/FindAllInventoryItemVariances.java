
package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event.InventoryItemVarianceFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.mapper.InventoryItemVarianceMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;


public class FindAllInventoryItemVariances extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemVariance> returnVal = new ArrayList<InventoryItemVariance>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemVariance", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemVarianceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemVarianceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
