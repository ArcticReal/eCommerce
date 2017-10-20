
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.categoryMember;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember.GlAccountCategoryMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.categoryMember.GlAccountCategoryMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryMember.GlAccountCategoryMember;


public class FindAllGlAccountCategoryMembers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountCategoryMember> returnVal = new ArrayList<GlAccountCategoryMember>();
try{
List<GenericValue> results = delegator.findAll("GlAccountCategoryMember", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountCategoryMemberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountCategoryMemberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
