package com.cly.ssi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cly.common.annotation.Validate;
import com.cly.common.web.json.Result;
import com.cly.ssi.entity.User;
import com.cly.ssi.service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "save")
	@ResponseBody
	@Validate
	public Object save(@RequestBody @Valid User user, BindingResult bindingResult) {
		Result result = new Result();
		try {
			userService.save(user);
			result.setMessage("保存用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
			result.setMessage("保存用户失败");
		}
		return result;
	}

	@RequestMapping(value = "selectById")
	@ResponseBody
	public Object selectById(int id) {
		Result result = new Result();
		try {
			User user = userService.selectById(id);
			result.setData(user);
			result.setMessage("查询用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
			result.setMessage("查询用户失败");
		}
		return result;
	}
}
