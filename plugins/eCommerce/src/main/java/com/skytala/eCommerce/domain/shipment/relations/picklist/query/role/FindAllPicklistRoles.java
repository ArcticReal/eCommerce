
package com.skytala.eCommerce.domain.shipment.relations.picklist.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.role.PicklistRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.role.PicklistRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.role.PicklistRole;


public class FindAllPicklistRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PicklistRole> returnVal = new ArrayList<PicklistRole>();
try{
List<GenericValue> results = delegator.findAll("PicklistRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PicklistRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PicklistRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
