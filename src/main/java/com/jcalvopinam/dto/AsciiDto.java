package com.jcalvopinam.dto;

import org.apache.commons.lang3.Validate;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class AsciiDto {

    private String text;
    private String font;
    private boolean url;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public boolean hasUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }

    public static boolean isNotNull(AsciiDto asciiDto){
        Validate.notNull(asciiDto, "The given asciDto object can't be null");
        Validate.notNull(asciiDto.getFont(), "The given font can't be null");
        Validate.notNull(asciiDto.getText(), "The given text can't be null");
        Validate.notNull(asciiDto.hasUrl(), "The given url can't be null");
        return true;
    }

}
