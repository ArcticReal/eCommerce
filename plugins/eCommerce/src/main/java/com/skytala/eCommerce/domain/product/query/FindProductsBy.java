package com.skytala.eCommerce.domain.product.query;
import java.util.*;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.product.event.ProductFound;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;

public class FindProductsBy extends Query {


    Map<String, String> filter;
    public FindProductsBy(Map<String, String> filter) {
        if(filter!=null)
            this.filter = filter;
        else
            this.filter = new HashMap<>();
    }

    @Override
    public Event execute(){


        Delegator delegator = DelegatorFactory.getDelegator("default");
        List<Product> foundProducts = new ArrayList<Product>();

        try{
            List<GenericValue> buf = new LinkedList<>();
            if(filter.size()==1&&filter.containsKey("productId")) {
                GenericValue foundElement = delegator.findOne("Product", false, filter);
                if(foundElement != null) {
                    buf.add(foundElement);
                }else {
                    throw new RecordNotFoundException(Product.class);
                }
            }else {
                buf = delegator.findAll("Product", false);
            }

            for (int i = 0; i < buf.size(); i++) {
                if(applysToFilter(buf.get(i))) {
                    foundProducts.add(ProductMapper.map(buf.get(i)));
                }
            }


        }catch(GenericEntityException e) {
            e.printStackTrace();
        }
        Event resultingEvent = new ProductFound(foundProducts);
        Broker.instance().publish(resultingEvent);
        return resultingEvent;

    }
    public boolean applysToFilter(GenericValue val) {

        Iterator<String> iterator = filter.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();

            if(val.get(key) == null) {
                return false;
            }

            if((val.get(key).toString()).contains(filter.get(key))) {
            }else {
                return false;
            }
        }
        return true;
    }
    public void setFilter(Map<String, String> newFilter) {
        this.filter = newFilter;
    }
}
