package com.binarfud.proplayer.challange5.dto.user.request.response;

import com.binarfud.proplayer.challange5.dto.merchant.response.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportItemDTO {
    private int weekNumber;
    private int monthNumber;
    private double totalIncome;
    private List<OrderDTO> orders;
}