package com.demisco.digishop.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllException(Exception e, HttpServletRequest request) {
        LOGGER.log(Level.SEVERE, "Unhandled exception occurred", e);
        if (e instanceof AccessDeniedException) {
            return new ModelAndView("403");
        }
        ModelAndView modelAndView = new ModelAndView("exception_handler");
        modelAndView.addObject("exception", e);
        modelAndView.addObject("URL", request.getRequestURL());
        return modelAndView;
    }
}
