package exception;

import com.alibaba.dubbo.common.json.JSON;
import com.enroy.demo.commons.biz.ActionResult;
import com.enroy.demo.web.support.ResponseActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class SpringExceptionHandler {
  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(value = {LogoutException.class})
  @ResponseActionResult
  public void dispatchException(Exception e, HttpServletResponse response, HttpServletRequest request) throws IOException {
    if (e instanceof LogoutException) {
      response.setStatus(1001);
      return;
    }
    logger.error(String.valueOf(request.getRequestURL()), e);

    response.setStatus(500);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().print(JSON.json(ActionResult.fail(e.getCause() != null ? e.getCause().getMessage() : e.getMessage())));
  }

}