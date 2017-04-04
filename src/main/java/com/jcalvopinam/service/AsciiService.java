package com.jcalvopinam.service;

import com.jcalvopinam.dto.AsciiDto;

import java.io.IOException;
import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface AsciiService {

    String generateAscii(AsciiDto asciiDto) throws IOException;

    List<String> findAllFonts();

}
