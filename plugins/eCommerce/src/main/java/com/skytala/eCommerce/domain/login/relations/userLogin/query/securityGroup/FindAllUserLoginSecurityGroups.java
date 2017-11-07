
package com.skytala.eCommerce.domain.login.relations.userLogin.query.securityGroup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityGroup.UserLoginSecurityGroupMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;


public class FindAllUserLoginSecurityGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UserLoginSecurityGroup> returnVal = new ArrayList<UserLoginSecurityGroup>();
try{
List<GenericValue> results = delegator.findAll("UserLoginSecurityGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UserLoginSecurityGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UserLoginSecurityGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
