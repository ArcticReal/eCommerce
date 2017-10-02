package com.skytala.eCommerce.domain.userLogin.command;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;

import com.skytala.eCommerce.domain.userLogin.event.UserLoginDeleted;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class DeleteUserLogin extends Command {

private String toBeDeletedId;
public DeleteUserLogin(String toBeDeletedId){
this.toBeDeletedId = toBeDeletedId;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success = false;

try{
int countRemoved = delegator.removeByAnd("UserLogin", UtilMisc.toMap("userLoginId", toBeDeletedId));
if(countRemoved > 0) {
success = true;
}
} catch (GenericEntityException e) {
e.printStackTrace();
}
Broker.instance().publish(new UserLoginDeleted(success));
return null;

}
public String getToBeDeletedId() {
return toBeDeletedId;
}
public void setToBeDeletedId(String toBeDeletedId) {
this.toBeDeletedId = toBeDeletedId;
}
}
