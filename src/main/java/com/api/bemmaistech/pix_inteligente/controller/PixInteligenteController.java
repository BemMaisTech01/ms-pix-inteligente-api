package com.api.bemmaistech.pix_inteligente.controller;

import com.api.bemmaistech.pix_inteligente.service.PixInteligenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/envio")
public class PixInteligenteController {

    final PixInteligenteService service;

    @Autowired
    public PixInteligenteController(PixInteligenteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> enviopdf(@RequestParam("file") MultipartFile multipartFile){
        try {
            service.processarArquivo(multipartFile);
            return ResponseEntity.ok("Arquivo enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao enviar o arquivo: " + e.getMessage());
        }
    }

}
