package vn.iostar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.iostar.entity.Customer;
import vn.iostar.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Chỉ ADMIN mới được truy cập endpoint này
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    // Cả ADMIN và USER đều có thể truy cập endpoint này
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String getCustomerById(@PathVariable String id, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "customer-detail";
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }
}