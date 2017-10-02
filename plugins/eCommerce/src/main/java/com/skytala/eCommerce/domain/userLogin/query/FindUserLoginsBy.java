package com.skytala.eCommerce.domain.userLogin.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

public class FindUserLoginsBy extends Query {

	Map<String, String> filter;

	public FindUserLoginsBy(Map<String, String> filter) {
		this.filter = filter;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<UserLogin> foundUserLogins = new ArrayList<UserLogin>();

		try {
			List<GenericValue> buf = delegator.findAll("UserLogin", false);
			for (int i = 0; i < buf.size(); i++) {
				if (applysToFilter(buf.get(i))) {
					foundUserLogins.add(UserLoginMapper.map(buf.get(i)));
				}
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new UserLoginFound(foundUserLogins));
		return null;

	}

	public boolean applysToFilter(GenericValue val) {

		Iterator<String> iterator = filter.keySet().iterator();

		while (iterator.hasNext()) {

			String key = iterator.next();

			if (val.get(key) == null) {
				return false;
			}

			if ((val.get(key).toString()).contains(filter.get(key))) {
			} else {
				return false;
			}
		}
		return true;
	}
	
	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}

}
