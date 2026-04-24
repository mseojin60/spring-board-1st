package com.github.springboard1st;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "<div style='text-align:center; margin-top:50px; font-family:sans-serif;'>" +
              "<h3>현재 백엔드 배포 중</h3>" +
               "<hr style='width:50%'>" +
               "<p style='color:gray;'>프론트엔드 연동 작업이 진행될 예정입니다.</p>" +
               "</div>";
    }
}
