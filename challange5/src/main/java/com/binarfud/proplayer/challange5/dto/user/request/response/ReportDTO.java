package com.binarfud.proplayer.challange5.dto.user.request.response;

import com.binarfud.proplayer.challange5.dto.merchant.response.ReportItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDTO {
    private List<ReportItemDTO> Reported;
}
