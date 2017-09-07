
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.UserLogin;
import com.skytala.eCommerce.entity.UserLoginMapper;
import com.skytala.eCommerce.event.UserLoginFound;

public class FindAllUserLogins implements Query {

	@Override
	public void execute() {

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
	}
}
