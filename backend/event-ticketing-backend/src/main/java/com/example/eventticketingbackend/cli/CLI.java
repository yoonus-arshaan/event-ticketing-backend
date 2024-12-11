package com.example.eventticketingbackend.cli;//package com.example.eventticketingbackend.cli;

import com.example.eventticketingbackend.EventTicketingBackendApplication;
import com.example.eventticketingbackend.model.Configuration;
import com.example.eventticketingbackend.model.Customer;
import com.example.eventticketingbackend.model.TicketPool;
import com.example.eventticketingbackend.model.Vendor;
import com.example.eventticketingbackend.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CLI {
    private static Configuration configuration;
    private static ConfigurationService configurationService = new ConfigurationService();
    public static Logger logger = LoggerFactory.getLogger(CLI.class);
    private static List<Thread> vendorThreads = new ArrayList<>();
    private static List<Thread> customerThreads = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting Ticketing System...");

        SpringApplication app = new SpringApplication(EventTicketingBackendApplication.class);
        // Turn off the banner and logging
        app.setBannerMode(Banner.Mode.OFF);
        System.setProperty("logging.level.root", "OFF");
        System.setProperty("logging.level.org.springframework" + ".boot", "OFF");
        System.setProperty("logging.level.org.springframework.web", "OFF");
        ApplicationContext context = app.run(args);

        Scanner scanner = new Scanner(System.in);
        displayWelcomeMessage();


        int choice = -1;
        boolean validInput = true;
        while (validInput) {
            displayOptions();
            String input = scanner.nextLine();

            if (isInteger(input)) {
                choice = Integer.parseInt(input);
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            switch (choice) {
                case 1 -> configureSystem(scanner);
                case 2 -> startSimulation();
                case 3 -> endSimulation();
                case 4 -> showConfiguration();
                case 5 -> {
                    System.out.println("Exiting system...");
//                    validInput = false;
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again (1-4).");
            }
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the Ticketing System!");
        System.out.println("================================");
    }

    private static void displayOptions() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Configure System");
        System.out.println("2. Start Simulation");
        System.out.println("3. End Simulation");
        System.out.println("4. Show configurations");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void configureSystem(Scanner scanner) {
        System.out.println("\nConfiguring the system...");

        boolean isValid = true;
        while(isValid) {
            try {
                System.out.print("Enter Total Number of Tickets: ");
                int totalTickets = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Ticket Release Rate (in seconds): ");
                int ticketReleaseRate = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Customer Retrieval Rate (in seconds): ");
                int customerRetrievalRate = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Maximum Ticket Capacity: ");
                int maxTicketCapacity = Integer.parseInt(scanner.nextLine());

                if (maxTicketCapacity < totalTickets) {
                    System.out.println("\nMax Ticket Capacity cannot exceed Total Tickets.");
                    continue;
                }
//                ticketPool = new TicketPool(maxTicketCapacity);

                // create and save configuration
                Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
                ConfigurationService configService = new ConfigurationService();
                configService.saveConfiguration(configuration);
                System.out.println("System configured successfully!");
                isValid = false;

            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter valid integers.\n");
            }
        }
    }

    private static void startSimulation() {
        if (configuration == null) {
            configuration = configurationService.loadConfiguration(); // Load configuration from the file
            if (configuration == null) { // If still null, show the error message
                logger.warn("Configuration is not loaded! Please configure the system first.");
                return;
            }
        }
//        configuration = configurationService.loadConfiguration();

        logger.info("Starting the simulation...\n");
        TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());


//        Vendor[] vendors = new Vendor[1];
        for (int i = 1; i <= 3; i++) {
            Vendor vendor = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendor, "Vendor-" + i);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }


//        Customer[] customers = new Customer[1];
        for (int i = 1; i <= 3; i++) {
            Customer customer = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), 5);
            Thread customerThread = new Thread(customer, "Customer-" + i);
            customerThreads.add(customerThread);
            customerThread.start();
        }

        // Wait for all vendor threads to finish
        for (Thread vendorThread : vendorThreads) {
            try {
                vendorThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Vendor thread interrupted: " + vendorThread.getName());
            }
        }

        // Wait for all customer threads to finish
        for (Thread customerThread : customerThreads) {
            try {
                customerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Customer thread interrupted: " + customerThread.getName());
            }
        }

        logger.info("Simulation completed!");
    }

    public static void endSimulation() {

        logger.info("Simulation ended!");
    }


    public static void showConfiguration() {
        if (configuration == null) {
            System.out.println("Configuration is not loaded! Please configure the system first.");
        }
        configuration = configurationService.loadConfiguration();
        System.out.println("\n Entered Configuration");
        System.out.println("-----------------------");
        System.out.println("Total tickets: " + configuration.getTotalTickets() +
                "\nCustomer retrieval rate: " + configuration.getCustomerRetrievalRate() +
                "\nTicket release rate: " + configuration.getTicketReleaseRate() +
                "\nMaximum ticket capacity: " + configuration.getMaxTicketCapacity());


    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str); // Try to parse the string as an integer
            return true; // Parsing successful
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }
}

