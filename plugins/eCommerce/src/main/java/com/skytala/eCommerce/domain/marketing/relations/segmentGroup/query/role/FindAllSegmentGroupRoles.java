
package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role.SegmentGroupRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.role.SegmentGroupRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;


public class FindAllSegmentGroupRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SegmentGroupRole> returnVal = new ArrayList<SegmentGroupRole>();
try{
List<GenericValue> results = delegator.findAll("SegmentGroupRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SegmentGroupRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SegmentGroupRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
