/*
package com.ruoyi.system.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.file.FileUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.mapper.SysOssMapper;
import com.ruoyi.system.service.ISysOssService;

import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("sysOssService")
public class SysOssServiceImpl implements ISysOssService
{
    @Autowired
    private SysOssMapper sysOssMapper;

    */
/*
     * (non-Javadoc)
     * 
     * @see
     * com.zmr.wind.modules.sys.service.ISysOssService#getList(com.zmr.wind.
     * modules.sys.entity.SysOss)
     *//*

    @Override
    public List<SysOss> getList(SysOss sysOss)
    {
        Example example = new Example(SysOss.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sysOss.getFileName()))
        {
            criteria.andLike("fileName", "%" + sysOss.getFileName() + "%");
        }
        if (StringUtils.isNotBlank(sysOss.getFileSuffix()))
        {
            criteria.andEqualTo("fileSuffix", sysOss.getFileSuffix());
        }
        if (StringUtils.isNotBlank(sysOss.getCreateBy()))
        {
            criteria.andLike("createBy", sysOss.getCreateBy());
        }
        return sysOssMapper.selectByExample(example);
    }

    */
/* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#save(com.ruoyi.system.domain.SysOss)
     *//*

    @Override
    public int save(SysOss ossEntity) {
*/
/*
        if (file.isEmpty())
        {
            throw new OssException("上传文件不能为空");
        }
        // 上传文件
        String fileName = file.getOriginalFilename();
        File newfile = FileUtils.changeFile(file);
        String filemd5 = DigestUtils.md5Hex(new FileInputStream(newfile));
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        CloudStorageService storage = OSSFactory.build();
        String url = storage.uploadSuffix(file.getBytes(), suffix);
        // 保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setUrl(url);
        ossEntity.setFileSuffix(suffix);
        ossEntity.setCreateBy(ShiroUtils.getLoginName());
        ossEntity.setFileName(fileName);
        ossEntity.setCreateTime(new Date());
        ossEntity.setService(storage.getService());
        ossEntity.setMd5(filemd5);*//*


        return sysOssMapper.insertSelective(ossEntity);
    }

    */
/* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#findById(java.lang.Long)
     *//*

    @Override
    public SysOss findById(Long ossId)
    {
        return sysOssMapper.selectByPrimaryKey(ossId);
    }

    */
/* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#update(com.ruoyi.system.domain.SysOss)
     *//*

    @Override
    public int update(SysOss sysOss)
    {
        return sysOssMapper.updateByPrimaryKeySelective(sysOss);
    }

    */
/* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#deleteByIds(java.lang.String)
     *//*

    @Override
    public int deleteByIds(String ids)
    {
        return sysOssMapper.deleteByIds(ids);
    }
}
*/
