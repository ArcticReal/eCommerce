package com.skytala.eCommerce.command;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.control.Command;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.events.ProductAdded;

public class AddProduct implements Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Broker.instance().publish(new ProductAdded(new Product("testnameBr","testIdBr")));
		
	}

}
