
package com.skytala.eCommerce.domain.party.relations.roleType.query.attr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.roleType.event.attr.RoleTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.roleType.mapper.attr.RoleTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.roleType.model.attr.RoleTypeAttr;


public class FindAllRoleTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RoleTypeAttr> returnVal = new ArrayList<RoleTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("RoleTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RoleTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RoleTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
