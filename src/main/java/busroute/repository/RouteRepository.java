package busroute.repository;

import busroute.businessExceptions.FileFormatException;
import busroute.businessExceptions.FileReadException;
import busroute.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Martyna on 2016-12-07.
 */
@Repository
public class RouteRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${busRouteFilePath}")
    private String busRouteFilePath;
    private List<Route> routeList = null;

    public List<Route> getRoutes() {
        if (routeList == null) {
            try (Stream<String> stream = Files.lines(Paths.get(busRouteFilePath))) {

                List<String> lines = stream.collect(toList());

                Integer numberOfRoutes = Integer.valueOf(lines.get(0));
                lines.remove(0);

                routeList = lines.stream().map(line -> RouteFactory.createRoute(line)).collect(toList());

                if (numberOfRoutes != routeList.size()) {
                    logger.error("File has wrong format!");
                    throw new FileFormatException();
                }

            } catch (IOException e) {
                logger.error("Error during reading file!", e);
                throw new FileReadException();
            } catch (NumberFormatException e) {
                logger.error("File has wrong format!", e);
                throw new FileFormatException();
            }
        }
        return new ArrayList<>(routeList);
    }
}
