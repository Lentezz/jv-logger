package mate.academy;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.AuthenticationServiceImpl;
import mate.academy.service.OrderService;
import mate.academy.service.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        User user;

        try {
            logger.info("Attempting to log in user with login: {}", "bob");
            user = authenticationService.login("bob", "1234");
            logger.info("User logged in successfully: id={}, login={}",
                    user.getUserId(), user.getLogin());
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for login: {}. Reason: {}", "bob",
                    e.getMessage());
            logger.debug("Stack trace:", e);
            return;
        }

        OrderService orderService = new OrderServiceImpl();
        logger.info("Starting to complete order for user id={}", user.getUserId());
        orderService.completeOrder(user.getUserId());
        logger.info("Order completed for user id={}", user.getUserId());

        logger.info("Application finished");
    }
}
