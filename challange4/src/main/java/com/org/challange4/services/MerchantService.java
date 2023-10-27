package com.org.challange4.services;

import com.org.challange4.dto.merchant.request.AddMerchantRequestDTO;
import com.org.challange4.dto.merchant.request.UpdateMerchantRequestDTO;
import com.org.challange4.dto.merchant.response.AddMerchantResponseDTO;
import com.org.challange4.dto.merchant.response.UpdateMerchantResponseDTO;
import com.org.challange4.models.Merchants;
import com.org.challange4.repository.MerchantRepository;
import com.org.challange4.util.LongConverterUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public AddMerchantResponseDTO addMerchant(AddMerchantRequestDTO addMerchantDTO) {
        Merchants merchants = merchantRepository.save(new Merchants(addMerchantDTO.getMerchantName(), addMerchantDTO.getMerchantLocation()));
        return convertToResponseDTO(merchants, "Merchant added successfully");
    }

    public UpdateMerchantResponseDTO updateMerchant(UUID id, UpdateMerchantRequestDTO updateMerchantRequestDTO) {
        Optional<Merchants> merchants = merchantRepository.findById(id);
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

    private AddMerchantResponseDTO convertToResponseDTO(Merchants merchants, String message) {
        AddMerchantResponseDTO responseDTO = new AddMerchantResponseDTO();
        responseDTO.setId(LongConverterUUID.convertLongToUUID(merchants.getId()));
        responseDTO.setMerchantName(merchants.getMerchantName());
        responseDTO.setMerchantLocation(merchants.getMerchantLocation());
        return responseDTO;
    }

    private UpdateMerchantResponseDTO convertToResponseDTO(Merchants merchant) {
        return new UpdateMerchantResponseDTO(merchant.getId(), merchant.getMerchantName(), merchant.getMerchantLocation(), merchant.getOpen());
    }
}
