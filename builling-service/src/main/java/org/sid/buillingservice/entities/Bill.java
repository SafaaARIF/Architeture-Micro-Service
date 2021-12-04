package org.sid.buillingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sid.buillingservice.model.Customer;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Bill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date BillingDate;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private long customerID;
    @Transient
    private Customer customer;
}
