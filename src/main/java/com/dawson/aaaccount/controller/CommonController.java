package com.dawson.aaaccount.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.dawson.aaaccount.entity.OperateResult;
import com.dawson.aaaccount.entity.User;

@RestController
@RequestMapping("/common")
public class CommonController {

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
	
	
	@RequestMapping("/getusers")
	public List<User> getUsers() {
		AVQuery<AVUser> query = new AVQuery<AVUser>("_User");

//		AVUser.loginWithAuthData(userInfo, callback);
//        if (TextUtils.isEmpty(familyId)) {
//            query.whereEqualTo(RECORDER, AVUser.createWithoutData("_User", "5b6004ab808ca4006fe99fd6"));
// 		                query.whereDoesNotExist(FAMILY);
//        } else {
//            val family = AVObject.createWithoutData(DataObjectHelper.FAMILY.CLASS_NAME, familyId)
//            query.whereEqualTo(DataObjectHelper.DAY_BOOK.FAMILY, family)
//        }
		query.limit(10);// 最多返回 10 条结果
		query.skip(0 * 10);// 跳过 20 条结果
//query.include(CONSUME_CATEGORY + "." + NAME).include(PAYER + "." + UNAME).include(PAYER2 + "." + MNAME);
//query.selectKeys(Arrays.asList(MONEY, CONSUME_CATEGORY + "." + NAME, PAYER + "." + UNAME, PAYER2 + "." + MNAME,
//	DATE, THUM_PICTURES));
		query.orderByDescending("updatedAt");
		List<AVUser> objs;
		try {
			objs = query.find();
		} catch (AVException e) {
			e.printStackTrace();
			return null;
		}
		List<User> users = new ArrayList<User>();
		objs.forEach(new Consumer<AVUser>() {

			@Override
			public void accept(AVUser avObject) {

				User duser = new User();
				duser.setId(avObject.getObjectId());
				duser.setCreateTime(avObject.getCreatedAt());
				duser.setUpdateTime(avObject.getUpdatedAt());
				duser.setName(avObject.getUsername());
				duser.setAuthData(avObject.getJSONObject("authData").toString());
//	String thumbPics = avObject.getString(THUM_PICTURES);
//                    		daybook.thumbPictures =Arrays.asList( thumbPics.split(";"));
				users.add(duser);
			}

		});
		return users;
	}
}
