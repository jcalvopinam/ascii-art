package com.jcalvopinam.controller;

import com.jcalvopinam.dto.AsciiDto;
import com.jcalvopinam.service.AsciiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AsciiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AsciiService generateAscii;

    @Mock
    AsciiDto asciiDto;

    @Before
    public void setup(){
        asciiDto = new AsciiDto();
        asciiDto.setFont("standard");
        asciiDto.setText("Juanca");
        asciiDto.setUrl(false);
    }

    @Test
    public void getFontsTest() {
        List<String> body = this.restTemplate.getForObject("/fonts", List.class);
        assertThat(body).isEqualTo(fontList());
    }

    @Test
    public void generateAsciiTest() throws IOException {
        String expectedResult = "      _                                        \n" +
                                "     | |  _   _    __ _   _ __     ___    __ _ \n" +
                                "  _  | | | | | |  / _` | | '_ \\   / __|  / _` |\n" +
                                " | |_| | | |_| | | (_| | | | | | | (__  | (_| |\n" +
                                "  \\___/   \\__,_|  \\__,_| |_| |_|  \\___|  \\__,_|\n" +
                                "                                               \n";
        String result = generateAscii.generateAscii(asciiDto);
        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(expectedResult);
    }


    private final List<String> fontList() {
        return Arrays.asList("SansSerif", "standard");
    }

}