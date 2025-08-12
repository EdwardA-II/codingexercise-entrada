package olis.codingexercise.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CustomErrorController implements ErrorController {

    // Added some logging to make it official!
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest errorRequest) {

        Integer statusCode = Integer.getInteger("javax.servlet.error.status_code");
        String message = (String) errorRequest.getAttribute("javax.servlet.error.message");
        Exception exception = (Exception) errorRequest.getAttribute("javax.servlet.error.exception");

        logger.error("An error occurred while processing the request.");
        logger.error("Error occurred: HTTP Status: {}, Message: {}", statusCode, message);

        // If an exception occurs, log it
//        if (exceptionDetails != null) {
//            logger.error("Exception details: {}", String.valueOf(exceptionDetails));
//        }

        return "error-general";
    }
}