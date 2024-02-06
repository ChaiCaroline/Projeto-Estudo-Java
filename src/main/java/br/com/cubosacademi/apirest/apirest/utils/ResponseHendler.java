package br.com.cubosacademi.apirest.apirest.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHendler {

    // to falando que vai retorna um objeto, pois como não sei, ele e tipo generico.
    public static ResponseEntity<Object> generate(
            String messagem,
            HttpStatus statusCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("messagem", messagem);

        return new ResponseEntity<Object>(map, statusCode);
    }
}
