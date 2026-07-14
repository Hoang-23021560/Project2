package com.javaweb.api;

import com.javaweb.model.BuildingResponse;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.CustomerResponse;
import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.model.TransactionResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // Dùng @Controller thường để trả về File HTML (View) thay vì JSON
public class WebViewController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    // 1. Giao diện trang danh sách Tòa nhà
    @GetMapping("/web/building")
    public String getBuildingPage(BuildingSearchRequest request, Model model) {
        List<BuildingResponse> buildings = buildingService.findAll(request);
        model.addAttribute("listBuildings", buildings);
        return "building-list"; // Trả về file building-list.html
    }

    // 2. Giao diện trang danh sách Khách hàng
    @GetMapping("/web/customer")
    public String getCustomerPage(CustomerSearchRequest request, Model model) {
        List<CustomerResponse> customers = customerService.findALl(request);
        model.addAttribute("listCustomers", customers);
        return "customer-list"; // Trả về file customer-list.html
    }

    // 3. Giao diện xem Lịch sử giao dịch của từng khách hàng
    @GetMapping("/web/transaction")
    public String getTransactionPage(@RequestParam("customerId") Long customerId, Model model) {
        List<TransactionResponse> transactions = transactionService.findByCustomerId(customerId);
        model.addAttribute("listTransactions", transactions);
        return "transaction-list"; // Trả về file transaction-list.html
    }
}