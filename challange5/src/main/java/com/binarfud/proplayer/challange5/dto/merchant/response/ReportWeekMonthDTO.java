package com.binarfud.proplayer.challange5.dto.merchant.response;


import com.binarfud.proplayer.challange5.dto.order.response.OrderInfoDTO;
import com.binarfud.proplayer.challange5.models.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportWeekMonthDTO {
    private int weekNumber;
    private int monthNumber;
    private double totalIncome;
    private List<OrderInfoDTO> orders;

}
