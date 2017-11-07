
package com.skytala.eCommerce.domain.login.relations.securityGroup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupFound;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.SecurityGroupMapper;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;


public class FindAllSecurityGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SecurityGroup> returnVal = new ArrayList<SecurityGroup>();
try{
List<GenericValue> results = delegator.findAll("SecurityGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SecurityGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SecurityGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
