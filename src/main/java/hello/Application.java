package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Jones"));
            repository.save(new Customer("Bill", "Gates"));
            repository.save(new Customer("Keith", "Flint"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("David", "Jones"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer find with findOne(1):");
            log.info("------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customers found with findByLastName('Jones'):");
            log.info("---------------------------------------------");
            for (Customer jones : repository.findByLastName("Jones")) {
                log.info(jones.toString());
            }
            log.info("");

            // fetch customers by first name
            log.info("Customers found with findByFirstName('Bill'):");
            log.info("---------------------------------------------");
            for (Customer bill : repository.findByFirstName("Bill")) {
                log.info(bill.toString());
            }
            log.info("");
        };
    }
}
