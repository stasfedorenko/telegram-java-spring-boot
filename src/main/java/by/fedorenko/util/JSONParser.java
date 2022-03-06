package by.fedorenko.util;

import by.fedorenko.entity.ReportDTO;
import by.fedorenko.entity.UserDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private static final Gson gson = new Gson();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static Map<UserDTO, List<ReportDTO>> readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            jsonText = jsonText.substring(jsonText.indexOf("<body>"), jsonText.indexOf("</body>"));
            jsonText = jsonText.substring(jsonText.indexOf("["));
            jsonText = jsonText.replaceAll("&#034;", "\"");
            return gson.fromJson(jsonText, new TypeToken<Map<UserDTO, List<ReportDTO>>>() {
            }.getType());
        }
    }

    public static Map<UserDTO, List<ReportDTO>> getJSON() throws IOException {
//        return (readJsonFromUrl("http://35.230.57.225:8080/main-java-app/apply?command=get_json_reports"));
        return gson.fromJson(getJSONString(), new TypeToken<Map<UserDTO, List<ReportDTO>>>() {
        }.getType());

    }


    public static String getJSONString(){
        return "[\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Nadya\",\n" +
                "            \"lastName\": \"Shokel\"\n" +
                "        },\n" +
                "        [\n" +
                "            {\n" +
                "                \"reportTitle\": \"Tomcat Setting up\",\n" +
                "                \"reportBody\": \"GCP tomcat installing; Setting Up. Testing Connection; Testing IP address\\r\\n\",\n" +
                "                \"laborCost\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Team Lead Job\",\n" +
                "                \"reportBody\": \"Setting up team task; Manage team work; Ensure team labor time;\",\n" +
                "                \"laborCost\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Microservice Architecture Task\",\n" +
                "                \"reportBody\": \"Development of microservice architecture; Creating application development structure\",\n" +
                "                \"laborCost\": 1\n" +
                "            }\n" +
                "        ]\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Maxim\",\n" +
                "            \"lastName\": \"Ananiev\"\n" +
                "        },\n" +
                "        [\n" +
                "            {\n" +
                "                \"reportTitle\": \"Java Email Apllication\",\n" +
                "                \"reportBody\": \"Setting up javax.mail; Creating plain java mail app\",\n" +
                "                \"laborCost\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Installing Java Mail App on GCP\",\n" +
                "                \"reportBody\": \"Testing Java Mail App; Installation it on GCP tomcat; testing\",\n" +
                "                \"laborCost\": 3\n" +
                "            }\n" +
                "        ]\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Yaroslav\",\n" +
                "            \"lastName\": \"Detskiy\"\n" +
                "        },\n" +
                "        [\n" +
                "            {\n" +
                "                \"reportTitle\": \"Creating Json parser\",\n" +
                "                \"reportBody\": \"Creating Json Parser; Testing it;\",\n" +
                "                \"laborCost\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Setting up Jenkins on GCP\",\n" +
                "                \"reportBody\": \"Jenkins Setting up;\",\n" +
                "                \"laborCost\": 2\n" +
                "            }\n" +
                "        ]\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Alexey\",\n" +
                "            \"lastName\": \"Bakulin\"\n" +
                "        },\n" +
                "        []\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Oleg\",\n" +
                "            \"lastName\": \"Akulov\"\n" +
                "        },\n" +
                "        [\n" +
                "            {\n" +
                "                \"reportTitle\": \"GCP Installation\",\n" +
                "                \"reportBody\": \"Creating Virtual Machines; Uploading Postres, Tomcat; Testing IP for avaliable in Internet;\",\n" +
                "                \"laborCost\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Setting Up Jenkins on GCP\",\n" +
                "                \"reportBody\": \"Created Jobs for auto deploy; Created Jobs for automatical start of Telegram java app and Mail Java App\",\n" +
                "                \"laborCost\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Setting Up Tomcat\",\n" +
                "                \"reportBody\": \"Configure Tomcat server on GCP. Installing JVM\",\n" +
                "                \"laborCost\": 2\n" +
                "            }\n" +
                "        ]\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Oleg\",\n" +
                "            \"lastName\": \"Dumava\"\n" +
                "        },\n" +
                "        [\n" +
                "            {\n" +
                "                \"reportTitle\": \"PDF Java Application\",\n" +
                "                \"reportBody\": \"Creating PDF Json parser; Testing it;\",\n" +
                "                \"laborCost\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"reportTitle\": \"Setting up Tomcat on GCP\",\n" +
                "                \"reportBody\": \"Setting up Tomcat, and configure it;\",\n" +
                "                \"laborCost\": 2\n" +
                "            }\n" +
                "        ]\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"firstName\": \"Stanislav\",\n" +
                "            \"lastName\": \"Fedorenko\"\n" +
                "        },\n" +
                "        [\n" +
                "            {\n" +
                "                \"reportTitle\": \"Telegram Java Application Task\",\n" +
                "                \"reportBody\": \"Setting up Telegram api; Creating Plain Java app for Telegram; Testing;\",\n" +
                "                \"laborCost\": 3\n" +
                "            }\n" +
                "        ]\n" +
                "    ]\n" +
                "]";
    }

}
