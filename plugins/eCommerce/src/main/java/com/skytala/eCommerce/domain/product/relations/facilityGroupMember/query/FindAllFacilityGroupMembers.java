
package com.skytala.eCommerce.domain.product.relations.facilityGroupMember.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.event.FacilityGroupMemberFound;
import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.mapper.FacilityGroupMemberMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.model.FacilityGroupMember;


public class FindAllFacilityGroupMembers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityGroupMember> returnVal = new ArrayList<FacilityGroupMember>();
try{
List<GenericValue> results = delegator.findAll("FacilityGroupMember", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityGroupMemberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityGroupMemberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
