package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.JwtRequest;
import za.ac.cput.GeneratorRental.domain.JwtResponse;
import za.ac.cput.GeneratorRental.service.JwtService;

@RestController
@CrossOrigin
@RequestMapping("generatorrental/jwt")
@Slf4j
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }
}
