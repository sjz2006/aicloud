package com.cn.spring.jpa;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * 
 * @author 苏坚昭
 *
 */
public class GlobalBasicErrorController extends BasicErrorController {

	public GlobalBasicErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
			List<ErrorViewResolver> errorViewResolvers) {
		super(errorAttributes, errorProperties, errorViewResolvers);
	}

	/**
	 * 定义500的ModelAndView
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(produces = "text/html", value = "/error/500")
	public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(getStatus(request).value());
		Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
		model.put("msg", "自定义错误信息");
		return new ModelAndView("error/500", model);
	}

	/**
	 * 定义500的错误JSON信息
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/error/500/restful")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
}
