
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.event.SalesOpportunityRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.mapper.SalesOpportunityRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.model.SalesOpportunityRole;


public class FindAllSalesOpportunityRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityRole> returnVal = new ArrayList<SalesOpportunityRole>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
