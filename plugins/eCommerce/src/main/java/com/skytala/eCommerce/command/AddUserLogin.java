package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.UserLogin;
import com.skytala.eCommerce.event.UserLoginAdded;

public class AddUserLogin implements Command {

private UserLogin elementToBeAdded;
public AddUserLogin(UserLogin elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try {
elementToBeAdded.setUserLoginId(delegator.getNextSeqId("UserLogin"));
GenericValue newValue = delegator.makeValue("UserLogin", elementToBeAdded.mapAttributeField());
delegator.create(newValue);
success = true;
} catch(GenericEntityException e) {
e.printStackTrace();
success = false;
}

Broker.instance().publish(new UserLoginAdded(success));
}
}
