
package com.skytala.eCommerce.domain.userLogin.query;

import java.util.LinkedList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.userLogin.event.UserLoginFound;
import com.skytala.eCommerce.domain.userLogin.mapper.UserLoginMapper;
import com.skytala.eCommerce.domain.userLogin.model.UserLogin;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllUserLogins extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<UserLogin> returnVal = new LinkedList<UserLogin>();
		try {
			List<GenericValue> results = delegator.findAll("UserLogin", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(UserLoginMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new UserLoginFound(returnVal));
		return null;
	}
}
