
package com.skytala.eCommerce.domain.login.relations.userLogin.query.passwordHistory;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.passwordHistory.UserLoginPasswordHistoryMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;


public class FindAllUserLoginPasswordHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UserLoginPasswordHistory> returnVal = new ArrayList<UserLoginPasswordHistory>();
try{
List<GenericValue> results = delegator.findAll("UserLoginPasswordHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UserLoginPasswordHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UserLoginPasswordHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
