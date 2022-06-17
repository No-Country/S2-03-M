package com.nocountry.travel;

import com.nocountry.travel.dto.MailRequestDTO;
import com.nocountry.travel.dto.MailResponse;
import com.nocountry.travel.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TravelApplication {

	@Autowired
	private EmailService emailService;
	@PostMapping("/mail")
	public MailResponse sendMail(@RequestBody MailRequestDTO request){
		return emailService.sendMail(request, null);
	}
	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

}
