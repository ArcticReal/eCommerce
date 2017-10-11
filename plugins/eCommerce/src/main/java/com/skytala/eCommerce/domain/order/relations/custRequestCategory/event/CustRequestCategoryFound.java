package com.skytala.eCommerce.domain.order.relations.custRequestCategory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestCategory.model.CustRequestCategory;
public class CustRequestCategoryFound implements Event{

	private List<CustRequestCategory> custRequestCategorys;

	public CustRequestCategoryFound(List<CustRequestCategory> custRequestCategorys) {
		this.custRequestCategorys = custRequestCategorys;
	}

	public List<CustRequestCategory> getCustRequestCategorys()	{
		return custRequestCategorys;
	}

}
