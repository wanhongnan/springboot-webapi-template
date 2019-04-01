package warp.home.core;
import java.util.Set;

import javax.servlet.http.*;
import javax.validation.*;
import org.slf4j.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
 
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
    /**
     * 系统异常处理，比如：404,500
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);
        JsonResult r = new JsonResult();
        r.setData(e.getMessage());
        if (e instanceof NoHandlerFoundException) {
             r.setCode(404);
             r.setMsg("Data Not Found");
        }else if (e instanceof ConstraintViolationException 
        		|| e instanceof MethodArgumentNotValidException) {
            r.setCode(333);
            r.setMsg("Validation failed");
        }
        else if(e instanceof ResultException) {
        	ResultException ex = (ResultException)e;
            r.setCode(ex.getCode());
            r.setMsg(ex.getMsg());    
            r.setData(ex.getData());
        }
        else {
             r.setCode(500);
             r.setMsg("Unknow Exception");
        }
        return r;
    }
}