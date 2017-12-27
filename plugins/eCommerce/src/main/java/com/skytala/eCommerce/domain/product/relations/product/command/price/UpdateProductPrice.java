package com.skytala.eCommerce.domain.product.relations.product.command.price;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.price.ProductPriceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPrice extends Command {

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
    public Event execute() throws RecordNotFoundException{



        Delegator delegator = DelegatorFactory.getDelegator("default");

        boolean success;
        try{
            GenericValue newValue = delegator.makeValue("ProductPrice", elementToBeUpdated.mapAttributeField());
            delegator.createOrStore(newValue);

            success = true;
        } catch (GenericEntityException e) {
            e.printStackTrace();
            if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
                throw new RecordNotFoundException(ProductPrice.class);
            }
            success = false;
        }
        Event resultingEvent = new ProductPriceUpdated(success);
        Broker.instance().publish(resultingEvent);
        return resultingEvent;
    }
}
