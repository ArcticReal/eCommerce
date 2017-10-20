
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.organization;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization.GlAccountOrganizationFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.organization.GlAccountOrganizationMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.organization.GlAccountOrganization;


public class FindAllGlAccountOrganizations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountOrganization> returnVal = new ArrayList<GlAccountOrganization>();
try{
List<GenericValue> results = delegator.findAll("GlAccountOrganization", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountOrganizationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountOrganizationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
