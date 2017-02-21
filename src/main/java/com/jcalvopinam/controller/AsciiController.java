package com.jcalvopinam.controller;

import com.jcalvopinam.service.AsciiService;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Controller
public class AsciiController {

    @Autowired
    AsciiService asciiService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String convert(@RequestBody String text) {
        Validate.notNull(text, "Cannot be null");
        return asciiService.convert(text);
    }

}
