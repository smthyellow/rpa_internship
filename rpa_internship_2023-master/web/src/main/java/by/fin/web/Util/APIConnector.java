package by.fin.web.Util;

import by.fin.module.entity.Currency;
import by.fin.module.entity.Rate;
import by.fin.service.RateService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor
public class APIConnector {

    public String getJson(URL url){
        String jsonString = "";
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responsecode = con.getResponseCode();

            if (responsecode == 404){

            } else if (responsecode != 200){
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    jsonString += scanner.nextLine();
                }

                scanner.close();

            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

}
