package com.skytala.eCommerce.domain.login.relations.userLogin.command.securityQuestion;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityQuestion.UserLoginSecurityQuestionMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUserLoginSecurityQuestion extends Command {

private UserLoginSecurityQuestion elementToBeAdded;
public AddUserLoginSecurityQuestion(UserLoginSecurityQuestion elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UserLoginSecurityQuestion addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("UserLoginSecurityQuestion", elementToBeAdded.mapAttributeField());
addedElement = UserLoginSecurityQuestionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UserLoginSecurityQuestionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
