/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author zouhairhajji
 */
@Controller
public class WelcomeController {


    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("CouCou", "maCouCou");
        
        return "/WEB-INF/jsp/welcome.jsp";
    }

}
