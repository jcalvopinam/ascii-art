package com.jcalvopinam.service;

import com.github.lalyos.jfiglet.FigletFont;
import com.jcalvopinam.dto.AsciiDto;
import com.jcalvopinam.dto.Fonts;
import com.jcalvopinam.font.CustomFont;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
public class AsciiServiceImpl implements AsciiService {

    /**
     * @param asciiDto
     * @return
     * @throws IOException
     */
    @Override
    public String generateAscii(AsciiDto asciiDto) throws IOException {
        String response;
        switch (asciiDto.getFont()) {
            case "SansSerif":
                System.out.println("Font chosen: " + asciiDto.getFont());
                response = (asciiDto.hasUrl()) ? CustomFont.convertOneLineImage(asciiDto.getText())
                                               : CustomFont.convertOneLineText(asciiDto.getText());
                break;
            case "standard":
                System.out.println("Font chosen: " + asciiDto.getFont());
                response = FigletFont.convertOneLine(asciiDto.getText());
                break;
            default:
                response = "Font doesn't found";
        }
        System.out.println(response);
        return response;
    }

    @Override
    public List<String> findAllFonts() {
        return Fonts.toList();
    }

}
