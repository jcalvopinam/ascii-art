package com.jcalvopinam.service;

import java.io.IOException;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface AsciiService {

    String convertText(String text);

    String convertImage(String url) throws IOException;

}
