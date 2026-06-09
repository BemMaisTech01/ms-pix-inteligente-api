package com.api.bemmaistech.pix_inteligente.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PixInteligenteService {

    @Value("${url.python}")
    private String url;


    public void processarArquivo(MultipartFile file) throws Exception {
        byte[] conteudo = file.getBytes();

        enviarParaPython(conteudo, file.getOriginalFilename());
    }

    private void enviarParaPython(byte[] conteudo, String nomeArquivo) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(conteudo) {
            @Override
            public String getFilename() {
                return nomeArquivo;
            }
        });

        String urlPython = url;

        restTemplate.postForObject(urlPython, body, String.class);
    }

}
