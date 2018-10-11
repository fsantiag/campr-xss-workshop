package com.thoughtworks.securityinourdna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final InvoiceRepo invoiceRepo;

    @Autowired
    public HomeController(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @GetMapping("/")
    public String home(Model model) throws Exception {
        model.addAttribute("invoices", invoiceRepo.all());
        return "home";
    }

    @PostMapping("/")
    public String addData(@RequestParam("invoice") String invoice) throws Exception {
        invoiceRepo.add(invoice);
        return "redirect:/";
    }
}
