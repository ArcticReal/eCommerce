package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.event.ProductPriceUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class UpdateProductPrice implements Command {

private ProductPrice elementToBeUpdated;

public UpdateProductPrice(ProductPrice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPrice getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPrice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public void execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPrice", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPrice.class); 
}
success = true;
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}
Broker.instance().publish(new ProductPriceUpdated(success));
}
}
