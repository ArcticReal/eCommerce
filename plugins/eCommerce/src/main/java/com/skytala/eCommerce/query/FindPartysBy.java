package com.skytala.eCommerce.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.entity.PartyMapper;
import com.skytala.eCommerce.event.PartyFound;

public class FindPartysBy implements Query {

	Map<String, String> filter;

	public FindPartysBy(Map<String, String> filter) {
		this.filter = filter;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Party> foundPartys = new ArrayList<Party>();

		try {
			List<GenericValue> buf = delegator.findAll("Party", false);
			for (int i = 0; i < buf.size(); i++) {
				if (applysToFilter(buf.get(i))) {
					foundPartys.add(PartyMapper.map(buf.get(i)));
				}
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new PartyFound(foundPartys));

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
}
