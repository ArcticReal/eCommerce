
package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role.ItemIssuanceRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.role.ItemIssuanceRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;


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
