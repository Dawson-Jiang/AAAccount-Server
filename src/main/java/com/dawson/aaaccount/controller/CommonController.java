package com.dawson.aaaccount.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	  /**
     * 实现文件上传
     * */
    @RequestMapping("/file_upload")
    public OperateResult<List<String>> fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
        	  return new OperateResult<>();
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        
        String path = "E:/test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            List<String> fs=new ArrayList<>();
            fs.add(fileName);
            fs.add("th"+fileName);
            return new OperateResult<>(fs);
        } catch (IllegalStateException e) { 
            e.printStackTrace();
            return new OperateResult<>();
        } catch (IOException e) { 
            e.printStackTrace();
            return new OperateResult<>();
        }
    }
	
	@RequestMapping("/sync_users")
	public OperateResult<String> syncUsers() {
		return  commonService.syncUserFromLeancloud();
	}
	
	@RequestMapping("/sync_category")
	public OperateResult<String> syncCategory() {
		return  commonService.syncCategoryFromLeancloud();
	}
	
	@RequestMapping("/sync_member")
	public OperateResult<String> syncMember() {
		return  commonService.syncMemberFromLeancloud();
	}
	
	@RequestMapping("/sync_family")
	public OperateResult<String> syncFamily() {
		return  commonService.syncFamilyFromLeancloud();
	}
}
