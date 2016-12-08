package busroute.businessExceptions;

/**
 * Created by Martyna on 2016-12-07.
 */
public class FileFormatException extends BusinessException {

    public FileFormatException() {
        super("File has wrong format!");
    }
}
