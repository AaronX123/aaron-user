package aaron.user.service.common.exception;

import aaron.common.data.exception.NestedExamException;
import aaron.common.data.exception.StarterError;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
public class UserException extends NestedExamException {
    public UserException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }

    public UserException(StarterError error){
        super(error.getMsg(),error.getCode());
    }

    public UserException(UserError error){
        super(error.getMsg(),error.getCode());
    }
}
