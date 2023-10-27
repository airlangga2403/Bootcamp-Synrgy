package com.org.challange4.services;

import com.org.challange4.dto.order.request.CreateOrderRequestDTO;
import com.org.challange4.dto.order.response.CreateOrderResponseDTO;
import com.org.challange4.dto.order.response.GetOrderResponseDTO;
import com.org.challange4.dto.order.response.OrderDetailResponseDTO;
import com.org.challange4.models.OrderDetail;
import com.org.challange4.models.Orders;
import com.org.challange4.models.Users;
import com.org.challange4.repository.OrderDetailRepository;
import com.org.challange4.repository.OrderRepository;
import com.org.challange4.repository.ProductRepository;
import com.org.challange4.repository.UserRepository;
import com.org.challange4.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public CreateOrderResponseDTO createOrder(UUID userId, CreateOrderRequestDTO createOrderRequestDTO) {
        Users user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            Orders order = new Orders();
            order.setUser(user);

            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
            Date orderTime = DateUtil.convertToDate(localDateTime);
            order.setOrder_time(orderTime);

            order.setDestinationAddress(createOrderRequestDTO.getDestinationAddress());
            order.setCompleted(true);

            Orders savedOrder = orderRepository.save(order);

            for (CreateOrderRequestDTO.OrderDetailDTO orderDetailDTO : createOrderRequestDTO.getOrderDetails()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(savedOrder);

                orderDetail.setProduct(productRepository.findById(orderDetailDTO.getProductId()).orElse(null));
                orderDetail.setQuantity(orderDetailDTO.getQuantity());
                orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice());

                orderDetailRepository.save(orderDetail);
            }

            // Add the saved order to the user's list of orders
            user.getOrders().add(savedOrder);
            userRepository.save(user);

            // Create a response DTO
            CreateOrderResponseDTO responseDTO = new CreateOrderResponseDTO();
            responseDTO.setId(savedOrder.getId());
            responseDTO.setOrder_time(savedOrder.getOrder_time());
            responseDTO.setDestinationAddress(savedOrder.getDestinationAddress());
            responseDTO.setCompleted(true);

            return responseDTO;
        }

        return null;
    }

    // GET ORDER
    public List<GetOrderResponseDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapOrderToDTO)
                .collect(Collectors.toList());
    }

    private GetOrderResponseDTO mapOrderToDTO(Orders order) {
        GetOrderResponseDTO responseDTO = new GetOrderResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setOrderTime(order.getOrder_time());
        responseDTO.setDestinationAddress(order.getDestinationAddress());
        responseDTO.setOrderDetails(mapOrderDetailsToDTO(order.getOrderDetails()));
        responseDTO.setCompleted(order.getCompleted());
        return responseDTO;
    }

    private List<OrderDetailResponseDTO> mapOrderDetailsToDTO(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(detail -> {
                    OrderDetailResponseDTO detailDTO = new OrderDetailResponseDTO();
                    detailDTO.setId(detail.getId());

                    // Check if the Product is not null before getting its ID
                    if (detail.getProduct() != null) {
                        detailDTO.setProductId(detail.getProduct().getId());
                    }
                    detailDTO.setQuantity(detail.getQuantity());
                    detailDTO.setTotalPrice(detail.getTotalPrice());
                    return detailDTO;
                })
                .collect(Collectors.toList());
    }


}
