package Util;

import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.openqa.selenium.Proxy;
import org.zaproxy.clientapi.core.ClientApiException;







public class ZapUtil {
    private static ClientApi clientApi;
    public static Proxy proxy ;

    private static final String zapAddress = "127.0.0.1" ;
    private static final int zapPort = 8080 ;
    private static final String apiKey = "your-api-key" ;


    static {
        clientApi= new ClientApi(zapAddress , zapPort, apiKey);
        proxy = new Proxy().setSslProxy(zapAddress+":"+zapPort).setHttpProxy(zapAddress+":"+zapPort);

    }

    public static void waitTillPassiveScanCompleted(){
        try{
            ApiResponse apiResponse=clientApi.pscan.recordsToScan();
            String tempval = ((ApiResponseElement)apiResponse).getValue();
            while(!tempval.equals("0")){
                System.out.println("Passive Scan is in progress");
                apiResponse=clientApi.pscan.recordsToScan();
                tempval = ((ApiResponseElement)apiResponse).getValue();
            }
            System.out.println("Passive Scan is completed");
        }catch(ClientApiException e){
            e.printStackTrace();
        }
    }

    public static void generateZapReport(String site_to_test) {
        String title = "ZAP-Report";
        String template = "traditional-html";
        String theme = null;
        String description = "description";
        String contexts = null;
        String sites = site_to_test;
        String sections = null;
        String includedconfidences = null;
        String includedrisks = null;
        String reportfilename = "zapReport";
        String reportfilenamepattern = null;
        String reportdir = System.getProperty("user.dir");
        String display = null;

        try {
            clientApi.reports.generate(title, template, theme, description, contexts, sites, sections,
                    includedconfidences, includedrisks, reportfilename, reportfilenamepattern, reportdir, display);
        } catch (ClientApiException e) {
            e.printStackTrace();
        }

    }

}
