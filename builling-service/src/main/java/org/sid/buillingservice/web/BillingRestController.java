package org.sid.buillingservice.web;

import org.sid.buillingservice.entities.Bill;
import org.sid.buillingservice.feign.CustomerRestClient;
import org.sid.buillingservice.feign.ProductItemRestClient;
import org.sid.buillingservice.model.Customer;
import org.sid.buillingservice.model.Product;
import org.sid.buillingservice.repository.BillRepository;
import org.sid.buillingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    private BillRepository billRepository ;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    // c beaucoup mieux que l'injection autowired
    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            Product product = productItemRestClient.getProductById(productItem.getProductID());
            //productItem.setProduct(product);
            productItem.setProductName(product.getName());
        });
        return bill;
    }
}
