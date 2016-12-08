package busroute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        String busRouteFilePath = "";
        if (args.length > 0){
            busRouteFilePath = args[0];
            new SpringApplicationBuilder(Application.class)
                    .properties("busRouteFilePath="+busRouteFilePath).run(args);
        } else{
            System.out.println("You must specify bus route file location as arg!");
        }
    }
}
