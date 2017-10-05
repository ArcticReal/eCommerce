
package com.skytala.eCommerce.domain.inventoryItemLabelType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.inventoryItemLabelType.event.InventoryItemLabelTypeFound;
import com.skytala.eCommerce.domain.inventoryItemLabelType.mapper.InventoryItemLabelTypeMapper;
import com.skytala.eCommerce.domain.inventoryItemLabelType.model.InventoryItemLabelType;


public class FindAllInventoryItemLabelTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemLabelType> returnVal = new ArrayList<InventoryItemLabelType>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemLabelType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemLabelTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemLabelTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
