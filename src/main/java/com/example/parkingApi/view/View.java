package com.example.parkingApi.view;

import com.example.parkingApi.model.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class View {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cost")
    public String cost() {
        return "cost";
    }

    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("driver", new Driver());
        return "start";
    }

    @GetMapping("/stop")
    public String stop(Model model) {
        model.addAttribute("driver", new Driver());
        return "stop";
    }
}
