package com.skytala.eCommerce.domain.userLogin.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.userLogin.event.UserLoginAdded;
import com.skytala.eCommerce.domain.userLogin.model.UserLogin;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddUserLogin extends Command {

	private UserLogin elementToBeAdded;

	public AddUserLogin(UserLogin elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			elementToBeAdded.setUserLoginId(delegator.getNextSeqId("UserLogin"));
			GenericValue newValue = delegator.makeValue("UserLogin", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Broker.instance().publish(new UserLoginAdded(success));
		return null;
	}
}
