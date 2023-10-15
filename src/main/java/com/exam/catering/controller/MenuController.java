package com.exam.catering.controller;

import com.exam.catering.domain.Menu;
import com.exam.catering.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@SecurityRequirement(name = "Bearer Authentication")
public class MenuController {
    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Operation(summary = "Get menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu is found"),
            @ApiResponse(responseCode = "404", description = "Menu is not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),})
    @GetMapping
    public ResponseEntity<Menu> getMenuList() {
        Menu menu = menuService.getMenu();
        if (menu != null) {
            log.info("Menu is found!");
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            log.info("Menu is not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
