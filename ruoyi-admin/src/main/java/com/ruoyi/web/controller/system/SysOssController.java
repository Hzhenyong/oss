package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.ValidatorUtils;
import com.ruoyi.framework.web.exception.user.OssException;
import com.ruoyi.oss.cloud.CloudConstant;
import com.ruoyi.oss.cloud.CloudStorageConfig;
import com.ruoyi.oss.cloud.CloudStorageService;
import com.ruoyi.oss.cloud.OSSFactory;
import com.ruoyi.oss.cloud.valdator.AliyunGroup;
import com.ruoyi.oss.cloud.valdator.QcloudGroup;
import com.ruoyi.oss.cloud.valdator.QiniuGroup;
import com.ruoyi.oss.domain.SysOss;
import com.ruoyi.oss.service.ISysOssService;

import com.ruoyi.system.service.ISysConfigService;

/*import com.ruoyi.web.controller.system.cloud.CloudConstant;
import com.ruoyi.web.controller.system.cloud.CloudConstant.CloudService;
import com.ruoyi.web.controller.system.cloud.CloudStorageConfig;
import com.ruoyi.web.controller.system.cloud.CloudStorageService;
import com.ruoyi.web.controller.system.cloud.OSSFactory;
import com.ruoyi.web.controller.system.cloud.valdator.AliyunGroup;
import com.ruoyi.web.controller.system.cloud.valdator.QcloudGroup;
import com.ruoyi.web.controller.system.cloud.valdator.QiniuGroup;*/
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

/**
 * 文件上传
 */
@Controller
@RequestMapping("system/oss")
public class SysOssController extends BaseController
{
    private String              prefix = "system/oss";

    private final static String KEY    = CloudConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    private ISysOssService sysOssService;

    @Autowired
    private ISysConfigService   sysConfigService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/oss";
    }

    /**
     * 列表
     */
    @RequestMapping("list")
    @RequiresPermissions("sys:oss:list")
    @ResponseBody
    public TableDataInfo list(SysOss sysOss)
    {
        startPage();
        List<SysOss> list = sysOssService.getList(sysOss);
        return getDataTable(list);
    }

    /**
     * 云存储配置信息
     */
    @RequestMapping("config")
    @RequiresPermissions("sys:oss:config")
    public String config(Model model)
    {
        String jsonconfig = sysConfigService.selectConfigByKey(CloudConstant.CLOUD_STORAGE_CONFIG_KEY);
        // 获取云存储配置信息
        CloudStorageConfig config = JSON.parseObject(jsonconfig, CloudStorageConfig.class);
        model.addAttribute("config", config);
        return prefix + "/config";
    }

    /**
     * 保存云存储配置信息
     */
    @RequestMapping("saveConfig")
    @RequiresPermissions("sys:oss:config")
    @ResponseBody
    public AjaxResult saveConfig(CloudStorageConfig config)
    {
        // 校验类型
        ValidatorUtils.validateEntity(config);
        if (config.getType() == CloudConstant.CloudService.QINIU.getValue())
        {
            // 校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }
        else if (config.getType() == CloudConstant.CloudService.ALIYUN.getValue())
        {
            // 校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }
        else if (config.getType() == CloudConstant.CloudService.QCLOUD.getValue())
        {
            // 校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }
        return toAjax(sysConfigService.updateValueByKey(KEY, new Gson().toJson(config)));
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:add")
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file) throws Exception
    {
        String save = sysOssService.save(file);
        if ("1".equals(save)){
            return AjaxResult.success("上传成功");
        }else {
            return  AjaxResult.warn("文件已存在,文件名为：" + save);
        }


    }

    /**
     * 修改
     */
    @GetMapping("edit/{ossId}")
    @RequiresPermissions("sys:oss:edit")
    public String edit(@PathVariable("ossId") Long ossId, Model model)
    {
        SysOss sysOss = sysOssService.findById(ossId);
        model.addAttribute("sysOss", sysOss);
        return prefix + "/edit";
    }

    @GetMapping("editor")
    @RequiresPermissions("sys:oss:add")
    public String editor()
    {
        return prefix + "/editor";
    }

    /**
     * 修改
     */
    @PostMapping("edit")
    @RequiresPermissions("sys:oss:edit")
    @ResponseBody
    public AjaxResult editSave(SysOss sysOss)
    {
        return toAjax(sysOssService.update(sysOss));
    }

    /**
     * 删除
     */
    @RequestMapping("remove")
    @RequiresPermissions("sys:oss:remove")
    @ResponseBody
    public AjaxResult delete(String ids)
    {
        return toAjax(sysOssService.deleteByIds(ids));
    }
}
