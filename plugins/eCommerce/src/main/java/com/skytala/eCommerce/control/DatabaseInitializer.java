package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

public class DatabaseInitializer {

	
	@PostConstruct
	public void init() {
		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		//new group store
		Map<String, Object> mapStoreGroup = new HashMap<>();
		mapStoreGroup.put("productStoreGroupId", "SKYTALA_GROUP");
		mapStoreGroup.put("productStoreGroupTypeId", "PSGT_AREA");
		mapStoreGroup.put("productStoreGroupName", "Skytala Group");
		mapStoreGroup.put("description", "The Skytala Group");
		
		//new store
		Map<String, Object> mapStore = new HashMap<>();
		mapStore.put("productStoreId", "SKYTALA_DIETMANNSR.");
		mapStore.put("primaryStoreGroupId", "SKYTALA_GROUP");
		mapStore.put("storeName", "Skytala Store Dietmannsried");
		
		//new store
		Map<String, Object> mapStore2 = new HashMap<>();
		mapStore2.put("productStoreId", "SKYTALA_MUNICH");
		mapStore2.put("primaryStoreGroupId", "SKYTALA_GROUP");
		mapStore2.put("storeName", "Skytala Store Munich");

		
		try {
//			List<GenericValue> purposes = delegator.findAll("ProductStoreGroupType", false);
//			System.out.println(purposes+"\n\n\n\n\n\n\n\n\n");
			
			GenericValue storeGroupValue = delegator.makeValue("ProductStoreGroup", mapStoreGroup);
			delegator.createOrStore(storeGroupValue);
			
			GenericValue storeValue = delegator.makeValidValue("ProductStore", mapStore);
			delegator.createOrStore(storeValue);

			storeValue = delegator.makeValidValue("ProductStore", mapStore2);
			delegator.createOrStore(storeValue);
			

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		
	}
}
