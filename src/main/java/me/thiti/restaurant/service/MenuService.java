package me.thiti.restaurant.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import me.thiti.restaurant.dto.MenuDto;
import me.thiti.restaurant.security.JwtAccessTokenService;

@Service
public class MenuService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtAccessTokenService tokenService;

    public List<MenuDto> getMenus() {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8090/menu";

        ResponseEntity<MenuDto[]> response = restTemplate.exchange( url, 
                                                                    HttpMethod.GET,
                                                                    entity,
                                                                    MenuDto[].class);

        MenuDto[] menus = response.getBody();
        return Arrays.asList(menus);
    }

    public MenuDto addMenu(MenuDto menu) {

        String token = tokenService.requestAccessToken();
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(menu,headers);

        String url = "http://localhost:8090/menu";

        ResponseEntity<MenuDto> response = restTemplate.exchange(   url, 
                                                                    HttpMethod.POST,
                                                                    entity, 
                                                                    MenuDto.class);
                                                                    
        return response.getBody();
        }
        
}
