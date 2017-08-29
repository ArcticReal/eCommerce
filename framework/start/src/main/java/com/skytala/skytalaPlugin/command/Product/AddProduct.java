package com.skytala.skytalaPlugin.command.Product;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.companyname.skytalaPlugin.services.Product;
import com.skytala.skytalaPlugin.command.Command;

public class AddProduct implements Command{

	
	private Product product;
	private GenericValue createdProduct = null;
	
	public AddProduct(Product newProduct){
		this.product = newProduct;
	}
	
	@Override
	public void execute() {
		
		Delegator delegator = DelegatorFactory.getDelegator("default");

		try {
			GenericValue newValue = delegator.makeValue("Product", product.mapAttributeField());
			setCreatedProduct(delegator.create(newValue));
			
		} catch (GenericEntityException e){
			e.printStackTrace();
		}

		
		
	}

	public GenericValue getCreatedProduct() {
		return createdProduct;
	}

	public void setCreatedProduct(GenericValue createdProduct) {
		this.createdProduct = createdProduct;
	}

}
