package com.dawson.aaaccount.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;
import com.dawson.aaaccount.service.CommonService;
import com.dawson.aaaccount.service.DaybookService;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Resource
	private CommonService commonService;
	
	@RequestMapping("/test")
	@ResponseBody
	public OperateResult<String> test() {
		return new OperateResult<>("success");
	}

	/**
	 * 实现文件上传
	 */
	@PostMapping("/file_upload")
	@ResponseBody
	public OperateResult<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.isEmpty()) {
			String saveFileName = file.getOriginalFilename();
			File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
				out.write(file.getBytes());
				out.flush();
				out.close();
				return new OperateResult<>("http://localhost:8080/upload/"+ saveFile.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return new OperateResult<>("");
			} catch (IOException e) {
				e.printStackTrace();
				return new OperateResult<>("");
			}
		} else {
			return new OperateResult<>("");
		}
	}

	@RequestMapping("/sync_users")
	public OperateResult<String> syncUsers() {
		return commonService.syncUserFromLeancloud();
	}

	@RequestMapping("/sync_category")
	public OperateResult<String> syncCategory() {
		return commonService.syncCategoryFromLeancloud();
	}

	@RequestMapping("/sync_member")
	public OperateResult<String> syncMember() {
		return commonService.syncMemberFromLeancloud();
	}

	@RequestMapping("/sync_family")
	public OperateResult<String> syncFamily() {
		return commonService.syncFamilyFromLeancloud();
	}
}
