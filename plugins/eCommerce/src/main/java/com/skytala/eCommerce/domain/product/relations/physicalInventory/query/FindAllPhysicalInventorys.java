
package com.skytala.eCommerce.domain.product.relations.physicalInventory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryFound;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.mapper.PhysicalInventoryMapper;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;


public class FindAllPhysicalInventorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PhysicalInventory> returnVal = new ArrayList<PhysicalInventory>();
try{
List<GenericValue> results = delegator.findAll("PhysicalInventory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PhysicalInventoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PhysicalInventoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
