
package com.skytala.eCommerce.domain.login.relations.userLogin.query.history;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.history.UserLoginHistoryMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;


public class FindAllUserLoginHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UserLoginHistory> returnVal = new ArrayList<UserLoginHistory>();
try{
List<GenericValue> results = delegator.findAll("UserLoginHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UserLoginHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UserLoginHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
