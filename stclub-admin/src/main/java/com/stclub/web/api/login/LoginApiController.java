package com.stclub.web.api.login;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.stclub.common.core.controller.BaseController;
import com.stclub.common.core.domain.AjaxResult;
import com.stclub.common.core.redis.RedisCache;
import com.stclub.common.utils.SecurityUtils;
import com.stclub.student.domain.StuUser;
import com.stclub.student.service.IStuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author ljx
 * @Date 2022/3/7 17:38
 **/
@Api(value = "用户登录模块", tags = {"用户登录模块"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/login")
public class LoginApiController extends BaseController {
    private final IStuUserService iStuUserService;

    private final RedisCache redisCache;
    /**
     * 前端获取验证码
     * @author ljx
     * @throws Exception
     */
    @ApiOperation(value = "获取登录验证码")
    @GetMapping("/kaptcha")
    public AjaxResult getKaptchaImage() throws Exception {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(126, 30, 5, 8);
        String tokenId = IdUtil.fastSimpleUUID();
        redisCache.setCacheObject(tokenId,lineCaptcha.getCode(),300, TimeUnit.SECONDS);
        Map<String,Object> params = new HashMap<>();
        params.put("codeImage",lineCaptcha.getImageBase64Data());
        params.put("imgcodetoken",tokenId);
        return AjaxResult.success(params);
    }

    /**
     * 登录验证
     * @author ljx
     * @param loginName 登录账号
     * @param password 密码
     * @param code 验证码
     * @return 返回登录状态
     */
    @ApiOperation(value = "用户登录验证",notes = "登录时返回'200'为登录成功，'201'为账号不存在，'202'为验证码错误，'203'为密码错误")
    @PostMapping("/userLogin")
    public AjaxResult login(
            @ApiParam(name = "loginName",value = "登录账号",type = "String",required = true)@RequestParam("loginName")String loginName,
            @ApiParam(name = "password",value = "密码",type = "String",required = true)@RequestParam("password")String password,
            @ApiParam(name = "code",value = "验证码",type = "String",required = true)@RequestParam("code")String code,
            @ApiParam(name = "imgcodetoken",value = "验证码id,存放在cookie里面，前端登录的时候传过来",type = "String",required = true)@RequestParam("imgcodetoken")String imgcodetoken
    ){

        //比较验证码
        String attribute = redisCache.getCacheObject(imgcodetoken);
        if (!code.equalsIgnoreCase(attribute))
            return new AjaxResult(201,"验证码错误");
        //查询账号是否存在
        StuUser stuUser = iStuUserService.getOne(new LambdaQueryWrapper<StuUser>().eq(StuUser::getUserAccount,loginName));
        if (stuUser == null){
            return new AjaxResult(202,"账号不存在！");
        }
        //判断密码是否存在
        if(!SecurityUtils.matchesPassword(password,stuUser.getPassword())){
            return new AjaxResult(203,"密码错误！");
        }
        //
        if(stuUser.getStatus() == 2){
            return new AjaxResult(204,"账号已停用，请联系管理员！");
        }
        Map map = new HashMap();
        map.put("userId",stuUser.getUserId());
        return AjaxResult.success(map);
    }


    /**
     * 设置5分钟后删除session中的验证码
     * @param attrName session中验证码的key
     */
    private void removeAttrCode(final String attrName) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 删除session中存的验证码
                getSession().removeAttribute(attrName);
                timer.cancel();
            }
        }, 5 * 60 * 1000);
    }

    /**
     * 用户注册
     * @param stuUser 用户数据
     * @return 注册状态
     */
    @ApiOperation(value = "用户注册",notes = "用户注册")
    @PostMapping("/register")
    public AjaxResult register(
            @RequestBody StuUser stuUser
    ){
        StuUser one = iStuUserService.getOne(new LambdaQueryWrapper<StuUser>().eq(StuUser::getUserAccount, stuUser.getUserAccount()));
        if(one == null ){
            stuUser.setPassword(SecurityUtils.encryptPassword(stuUser.getPassword()));
            iStuUserService.save(stuUser);
            return AjaxResult.success("注册成功！");
        }
        return new AjaxResult(201,"账号已被注册！");
    }




}
