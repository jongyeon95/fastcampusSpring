package com.example.study.model.network.response;

import com.example.study.model.entity.OrderGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderInfoApiResponse {
    private UserApiResponse userApiResponse;

}
