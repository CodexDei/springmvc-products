package com.codexdei.springmvc.productwebapp.productwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codexdei.springmvc.productwebapp.productwebapp.utils.IpUtils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IpController {

    @GetMapping("/show-ip")
    public String showIp(HttpServletRequest request, Model model){

        String ipClient = IpUtils.getClientIp(request);
        model.addAttribute("ipclient", ipClient);

        return "show-ip";
    }
}
