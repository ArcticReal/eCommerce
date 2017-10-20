
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.groupMember;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember.GlAccountGroupMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.groupMember.GlAccountGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember.GlAccountGroupMember;


public class FindAllGlAccountGroupMembers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountGroupMember> returnVal = new ArrayList<GlAccountGroupMember>();
try{
List<GenericValue> results = delegator.findAll("GlAccountGroupMember", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountGroupMemberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountGroupMemberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
