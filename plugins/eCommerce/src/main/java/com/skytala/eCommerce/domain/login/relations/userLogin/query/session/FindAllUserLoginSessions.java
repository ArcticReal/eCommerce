
package com.skytala.eCommerce.domain.login.relations.userLogin.query.session;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.session.UserLoginSessionMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;


public class FindAllUserLoginSessions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UserLoginSession> returnVal = new ArrayList<UserLoginSession>();
try{
List<GenericValue> results = delegator.findAll("UserLoginSession", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UserLoginSessionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UserLoginSessionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
