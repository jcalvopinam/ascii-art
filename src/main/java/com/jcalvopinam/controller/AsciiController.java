package com.jcalvopinam.controller;

import com.jcalvopinam.dto.AsciiDto;
import com.jcalvopinam.service.AsciiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@RestController
public class AsciiController {

    @Autowired
    AsciiService asciiService;

    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String generateAscii(@RequestBody AsciiDto asciiDto) throws IOException {
        AsciiDto.isNotNull(asciiDto);
        return asciiService.generateAscii(asciiDto);
    }

    @RequestMapping(value = "/fonts", method = RequestMethod.GET)
    public List<String> getFonts() {
        return asciiService.findAllFonts();
    }

}

