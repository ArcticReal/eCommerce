package com.skytala.eCommerce.command;

import com.skytala.eCommerce.entity.ProductPrice;

public class AddProductPrice implements Command{

	ProductPrice productPriceToBeAdded;
	
	public AddProductPrice(ProductPrice productPriceToBeAdded) {
		this.productPriceToBeAdded = productPriceToBeAdded;
	}
	
	@Override
	public void execute() {

		
		
	}

}
