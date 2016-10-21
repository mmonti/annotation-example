package com.dreamworks.annotation;

import com.dreamworks.annotation.config.Eval;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mmonti on 10/20/16.
 */
@RestController
public class AnnotationController {

    @RequestMapping(value = "/parameter")
    public ResponseEntity<String> parameter(@Eval @RequestParam(value = "${auth.token.name}", required = false, defaultValue = "DEFAULT_PARAM") String param) {
        return ResponseEntity.ok(param);
    }

    @RequestMapping(value = "/no-eval")
    public ResponseEntity<String> noEval(@RequestParam(value = "${auth.token.name}", required = false) String param) {
        return ResponseEntity.ok(param);
    }

    @RequestMapping(value = "/header")
    public ResponseEntity<String> header(@Eval @RequestHeader(value = "${auth.token.name}", required = false, defaultValue = "DEFAULT_HEADER") String header) {
        return ResponseEntity.ok(header);
    }

}
