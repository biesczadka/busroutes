package busroute.businessExceptions;

/**
 * Created by Martyna on 2016-12-07.
 */
public abstract class BusinessException extends RuntimeException{

    public BusinessException(String s) {
        super(s);
    }
}
