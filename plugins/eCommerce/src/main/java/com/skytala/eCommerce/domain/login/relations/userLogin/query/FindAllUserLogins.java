
package com.skytala.eCommerce.domain.login.relations.userLogin.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.UserLoginMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.UserLogin;


public class FindAllUserLogins extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UserLogin> returnVal = new ArrayList<UserLogin>();
try{
List<GenericValue> results = delegator.findAll("UserLogin", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UserLoginMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UserLoginFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
