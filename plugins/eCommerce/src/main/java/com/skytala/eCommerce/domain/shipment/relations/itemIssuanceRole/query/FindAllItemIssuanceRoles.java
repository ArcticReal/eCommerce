
package com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.event.ItemIssuanceRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.mapper.ItemIssuanceRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.model.ItemIssuanceRole;


public class FindAllItemIssuanceRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ItemIssuanceRole> returnVal = new ArrayList<ItemIssuanceRole>();
try{
List<GenericValue> results = delegator.findAll("ItemIssuanceRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ItemIssuanceRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ItemIssuanceRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
