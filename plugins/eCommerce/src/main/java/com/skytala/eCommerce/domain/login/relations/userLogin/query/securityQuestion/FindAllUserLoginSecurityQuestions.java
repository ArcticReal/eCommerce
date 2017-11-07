
package com.skytala.eCommerce.domain.login.relations.userLogin.query.securityQuestion;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionFound;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityQuestion.UserLoginSecurityQuestionMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;


public class FindAllUserLoginSecurityQuestions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UserLoginSecurityQuestion> returnVal = new ArrayList<UserLoginSecurityQuestion>();
try{
List<GenericValue> results = delegator.findAll("UserLoginSecurityQuestion", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UserLoginSecurityQuestionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UserLoginSecurityQuestionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
