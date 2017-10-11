
package com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.event.InventoryItemTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.mapper.InventoryItemTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.model.InventoryItemTypeAttr;


public class FindAllInventoryItemTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemTypeAttr> returnVal = new ArrayList<InventoryItemTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
