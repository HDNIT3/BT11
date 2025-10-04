package vn.iostar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.iostar.entity.Customer;
import vn.iostar.entity.UserInfo;
import vn.iostar.repository.CustomerRepository;
import vn.iostar.repository.UserInfoRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Tạo user admin
        if (userInfoRepository.findByUsername("admin").isEmpty()) {
            UserInfo admin = new UserInfo();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Administrator");
            admin.setEmail("admin@example.com");
            admin.setRoles("ADMIN");
            userInfoRepository.save(admin);
        }

        // Tạo user thường
        if (userInfoRepository.findByUsername("user").isEmpty()) {
            UserInfo user = new UserInfo();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setFullName("Regular User");
            user.setEmail("user@example.com");
            user.setRoles("USER");
            userInfoRepository.save(user);
        }

        // Tạo dữ liệu customer mẫu
        if (customerRepository.count() == 0) {
            Customer customer1 = new Customer("001", "Nguyen Van A", "nguyenvana@email.com", "0123456789", "Ha Noi");
            Customer customer2 = new Customer("002", "Tran Thi B", "tranthib@email.com", "0987654321", "Ho Chi Minh");
            Customer customer3 = new Customer("003", "Le Van C", "levanc@email.com", "0112233445", "Da Nang");
            
            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
        }
    }
}