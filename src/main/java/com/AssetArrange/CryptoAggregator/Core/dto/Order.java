package com.AssetArrange.CryptoAggregator.Core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    String id;
    String size;
    String price;
    String product_id;
    String side;
    String stp;
    String type;
    String time_in_force;
    String post_only;
    String created_at;
    String fill_fees;
    String filled_size;
    String executed_value;
    String status;
    Boolean settled;

    public String toString() {
        return getSide()
                + ": " + getProduct_id()
                + ": " + getPrice()
                + ": " + getSize();
    }
}
