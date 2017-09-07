package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.UserLogin;
import com.skytala.eCommerce.event.UserLoginUpdated;

public class UpdateUserLogin implements Command {

private UserLogin elementToBeUpdated;

public UpdateUserLogin(UserLogin elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLogin getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLogin elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLogin", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
success = false;
}
Broker.instance().publish(new UserLoginUpdated(success));
}
}
