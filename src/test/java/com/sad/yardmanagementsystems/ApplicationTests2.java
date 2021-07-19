package com.sad.yardmanagementsystems;
import java.net.URI;  
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Assertions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sad.yardmanagementsystem.controller.dto.MyPojo;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.service.UtenteService;
import com.sad.yardmanagementsystem.service.UtenteServiceImpl;

import net.minidev.json.JSONObject;





@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ApplicationTests2 {
    @Autowired
    private UtenteService utenteService;

   //test eseguito con le seguenti precondizioni: 
   //Esista un movimento di ingresso per una data prenotazione esistente

   
   @Test
    public void utenteGiaProcessato() throws URISyntaxException, InterruptedException, JsonMappingException, JsonProcessingException 
    {
        final String baseUrl = "http://localhost:8080/api/home_external_system/update_book_information";        
        //Precondizione: verifico che l'utente EXTERNAL_SYSTEM_SCANNER esista
        Assertions.assertDoesNotThrow(()->utenteService.loadUserByMail("test@test.it"));        
        String url = "http://localhost:8080/api/home_external_system/update_book_information";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("user", "test@test.it");
        map.put("password", "Ciao1!");
        map.put("key", "1fe2fc3a7-1035-4b9b-a22a-32df44336a37");
        map.put("type", "in");
        map.put("timestamp_reg","2021-07-18 20:53:47");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        Assert.assertEquals(204, response.getStatusCodeValue());
      	    
      	    
    }
     

   
 
}