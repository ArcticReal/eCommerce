package com.skytala.eCommerce.query;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.entity.PartyMapper;

public class FindPartysById implements Query {

	private String generalId;

	public FindPartysById(String generalId) {
		this.generalId = generalId;
	}

	public String getGeneralId() {
		return generalId;
	}

	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Party> foundEntries = new LinkedList<>();

		try {

			EntityCondition cond = EntityCondition.makeCondition("PartyId", generalId);
			List<String> orderBy = new LinkedList<>();
			orderBy.add("PartyId DESC");
			List<GenericValue> values = delegator.findList("Party", cond, null, orderBy, null, false);
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			currentDate.setNanos(0);

			for (int i = 0; i < values.size(); i++) {
				Party entry = PartyMapper.map(values.get(i));

				foundEntries.add(entry);
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new PartysFound(foundEntries));
	}
}
