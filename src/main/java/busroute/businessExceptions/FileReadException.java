package busroute.businessExceptions;

/**
 * Created by Martyna on 2016-12-07.
 */
public class FileReadException extends BusinessException {

    public FileReadException() {
        super("Can't read file!");
    }
}
