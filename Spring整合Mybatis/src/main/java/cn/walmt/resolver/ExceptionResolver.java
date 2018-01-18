package cn.walmt.resolver;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import util.GsonUtil;

@ControllerAdvice
public class ExceptionResolver {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e){
        e.printStackTrace();
        if (e instanceof NullPointerException){
            return GsonUtil.getErrorJson("NullPointerException");
        }
        return GsonUtil.getErrorJson(e.getMessage());
    }


}
