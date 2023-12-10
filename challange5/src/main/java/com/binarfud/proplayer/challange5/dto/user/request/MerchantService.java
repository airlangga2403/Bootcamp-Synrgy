package com.binarfud.proplayer.challange5.dto.user.request;

import com.binarfud.proplayer.challange5.dto.user.request.request.AddMerchantRequestDTO;
import com.binarfud.proplayer.challange5.dto.user.request.request.UpdateMerchantRequestDTO;
import com.binarfud.proplayer.challange5.dto.user.request.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public AddMerchantResponseDTO addMerchant(AddMerchantRequestDTO addMerchantDTO) {
        Optional<Users> users = userRepository.findById(addMerchantDTO.getUserId());
        if (users.isPresent()) {
            Merchants merchants = new Merchants(addMerchantDTO.getMerchantName(), addMerchantDTO.getMerchantLocation(), users.get());
            merchantRepository.save(merchants);
            return convertToResponseDTO(merchants, "Merchant added successfully");
        } else {
            return null;
        }
    }

    public UpdateMerchantResponseDTO updateMerchant(UUID uuid, UpdateMerchantRequestDTO updateMerchantRequestDTO) {
        Optional<Merchants> merchants = merchantRepository.findById(uuid);

        if (merchants.isPresent()) {
            Merchants existingMerchant = merchants.get();
            existingMerchant.setMerchantName(updateMerchantRequestDTO.getMerchantName());
            existingMerchant.setMerchantLocation(updateMerchantRequestDTO.getMerchantLocation());
            existingMerchant.setOpen(updateMerchantRequestDTO.getOpen());
            Merchants updatedMerchant = merchantRepository.save(existingMerchant);
            return convertToResponseDTO(updatedMerchant);
        } else {
            return null;
        }
    }

    // GetOpen Merchant
    public List<Merchants> getMerchantOpen() {
        return merchantRepository.getOpenMerchants();
    }

    public ReportDTO getReport() {
        List<Object[]> resultList = orderRepository.findAllWithWeekAndMonthNumbers();
        Map<String, ReportItemDTO> reportData = new LinkedHashMap<>();

        for (Object[] result : resultList) {
            UUID orderId = (UUID) result[2];
            int weekNumber = ((BigDecimal) result[5]).intValue();
            int monthNumber = ((BigDecimal) result[6]).intValue();
            double totalIncome = ((Double) result[7]);

            Optional<Orders> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                String key = "weekNumber:" + weekNumber + ", monthNumber:" + monthNumber;
                if (!reportData.containsKey(key)) {
                    ReportItemDTO reportItemDTO = new ReportItemDTO();
                    reportItemDTO.setWeekNumber(weekNumber);
                    reportItemDTO.setMonthNumber(monthNumber);
                    reportItemDTO.setTotalIncome(totalIncome);
                    reportItemDTO.setOrders(new ArrayList<>());
                    reportData.put(key, reportItemDTO);
                }

                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrder(order.get());

                reportData.get(key).getOrders().add(orderDTO);
            }
        }

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setReported(new ArrayList<>(reportData.values()));
        return reportDTO;
    }


    private AddMerchantResponseDTO convertToResponseDTO(Merchants merchants, String message) {
        AddMerchantResponseDTO responseDTO = new AddMerchantResponseDTO();
        responseDTO.setId(merchants.getId());
        responseDTO.setMerchantName(merchants.getMerchantName());
        responseDTO.setMerchantLocation(merchants.getMerchantLocation());
        responseDTO.setMessage(message);
        return responseDTO;
    }

    private UpdateMerchantResponseDTO convertToResponseDTO(Merchants merchant) {
        return new UpdateMerchantResponseDTO(merchant.getId(), merchant.getMerchantName(), merchant.getMerchantLocation(), merchant.getOpen());
    }
}

