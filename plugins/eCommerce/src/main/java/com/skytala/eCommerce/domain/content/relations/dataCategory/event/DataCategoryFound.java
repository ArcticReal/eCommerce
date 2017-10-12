package com.skytala.eCommerce.domain.content.relations.dataCategory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataCategory.model.DataCategory;
public class DataCategoryFound implements Event{

	private List<DataCategory> dataCategorys;

	public DataCategoryFound(List<DataCategory> dataCategorys) {
		this.dataCategorys = dataCategorys;
	}

	public List<DataCategory> getDataCategorys()	{
		return dataCategorys;
	}

}
