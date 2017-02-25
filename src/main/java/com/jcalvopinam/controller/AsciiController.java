package com.jcalvopinam.controller;

import com.jcalvopinam.service.AsciiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Controller
public class AsciiController {

    @Autowired
    AsciiService asciiService;

    @RequestMapping(value = "/convert", method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String convert(@RequestBody String text) {
        return asciiService.convert(text);
    }

}
