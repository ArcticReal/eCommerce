
package com.skytala.eCommerce.domain.product.relations.inventoryItem.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.attribute.InventoryItemAttributeFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.attribute.InventoryItemAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.attribute.InventoryItemAttribute;


public class FindAllInventoryItemAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemAttribute> returnVal = new ArrayList<InventoryItemAttribute>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
