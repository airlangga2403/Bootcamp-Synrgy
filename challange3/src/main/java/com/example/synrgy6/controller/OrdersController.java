package com.example.synrgy6.controller;

import com.example.synrgy6.model.OrderDetail;
import com.example.synrgy6.model.Orders;
import com.example.synrgy6.model.Products;
import com.example.synrgy6.service.OrderDetailService;
import com.example.synrgy6.service.OrdersService;
import com.example.synrgy6.service.ProductService;
import com.example.synrgy6.view.BinarFudView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
@Transactional
public class OrdersController {
    private final OrdersService ordersService;
    private final ProductService productService;
    private final OrderDetailService orderDetailService;

    private final BinarFudView view = new BinarFudView();
    private static final Scanner inpScanner = new Scanner(System.in);
    ArrayList<Integer> tempTotalPrice = new ArrayList<>();
    ArrayList<OrderDetail> orderDetails = new ArrayList<>();
    ArrayList<Integer> orderQty = new ArrayList<>();
    ArrayList<Products> tempProducts = new ArrayList<>();

    @Autowired
    public OrdersController(OrdersService ordersService, ProductService productService, OrderDetailService orderDetailService) {
        this.productService = productService;
        this.ordersService = ordersService;
        this.orderDetailService = orderDetailService;
    }

    public void addOrder() {
        view.printMenu(productService.getProduct());
        int choice = inpScanner.nextInt();


        if (choice > 0 && choice <= productService.getProduct().size()) {
            Products selectedProduct = productService.getProduct().get(choice - 1);
            tempProducts.add(selectedProduct);
            Orders newOrder = new Orders();
            newOrder.setOrderTime(LocalDateTime.now());
            view.inputUserAddress();
            String destinationAddress = inpScanner.next();

            if (destinationAddress != null) {
                newOrder.setDestinationAddress(destinationAddress);
                newOrder.setUserId(1L); // Assuming the user is logged in, set the correct user ID.
                newOrder.setCompleted(false);

                // Save the new order to the database using OrdersService
                newOrder = ordersService.createOrder(newOrder);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProductId(selectedProduct.getId());
                orderDetail.setOrderId(newOrder.getId());

                view.inputQuantity();
                int quantity = inpScanner.nextInt();

                if (quantity > 0) {
                    orderDetail.setQuantity(quantity);
                    orderQty.add(quantity);

                    // Calculate and display the total price
                    double totalPrice = selectedProduct.getPrice() * quantity;
                    orderDetail.setTotalPrice((int) totalPrice);
                    tempTotalPrice.add((int) totalPrice); // Add the total price to the list

                    // Save the order detail to the database using OrderDetailService
                    orderDetailService.createOrderDetail(orderDetail);

                    // Add the order detail to the list
                    orderDetails.add(orderDetail);

                    System.out.println("ORDER ID " + orderDetail.getProductId());

                    view.choiceMenu();
                    int choiceUser = inpScanner.nextInt();
                    if (choiceUser == 99) {
                        confirmAndPay(tempTotalPrice, orderDetails, tempProducts);
                        return;
                    } else if (choiceUser == 0) {
                        System.exit(0);
                    } else if (choiceUser == 88) {
                        addOrder();
                    }
//                    }
                } else {
                    view.errorInputMessage();
                }
            } else {
                view.errorInputMessage();
            }
        } else {
            view.errorInputMessage();
        }
    }

    private void confirmAndPay(ArrayList<Integer> tempTotalPrice, ArrayList<OrderDetail> orderDetails, ArrayList<Products>
            products) {
        // Implement payment processing logic here
        int totalPayment = tempTotalPrice.stream().mapToInt(Integer::intValue).sum();
        view.invoicePayment(); // view Tampilkan Total Order

        // Fetch the product's name outside the session scope
        view.orderSummary(orderDetails, products); // Pass the order details
        view.choice();
        int choiceUser = inpScanner.nextInt();
        if (choiceUser == 1) {
            view.totalAmount(totalPayment, orderQty.stream().mapToInt(Integer::intValue).sum());
            view.thankYouMessage();
        } else if (choiceUser == 2) {
            addOrder();
        } else if (choiceUser == 0) {
            System.exit(0);
        }
    }

}
