package com.ruoyi.oss.service.impl;


import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.exception.user.OssException;
import com.ruoyi.oss.cloud.CloudStorageService;
import com.ruoyi.oss.cloud.OSSFactory;
import com.ruoyi.oss.domain.SysOss;
import com.ruoyi.oss.mapper.SysOssMapper;
import com.ruoyi.oss.service.ISysOssService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("sysOssService")
public class SysOssServiceImpl implements ISysOssService
{
    @Autowired
    private SysOssMapper sysOssMapper;


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

    /* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#save(com.ruoyi.system.domain.SysOss)
     */
    @Override
    public int save(SysOss ossEntity) {
        return sysOssMapper.insertSelective(ossEntity);
    }


    @Override
    public String save(MultipartFile file) {
        SysOss ossEntity = null;

        try {
            String fileName = file.getOriginalFilename();
            File newfile = FileUtils.changeFile(file);
            String filemd5 = DigestUtils.md5Hex(new FileInputStream(newfile));
            //判断该文件在数据库中是否存在
            List<SysOss> byMd5 = findByMd5(filemd5);
            if (byMd5.size() > 0){
                //throw new RuntimeException("文件已存在文件名字为" + byMd5.get(0).getFileName());
                return byMd5.get(0).getFileName();
            }
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            CloudStorageService storage = OSSFactory.build();
            String url = storage.uploadSuffix(file.getBytes(), suffix);
            // 保存文件信息
            ossEntity = new SysOss();
            ossEntity.setUrl(url);
            ossEntity.setFileSuffix(suffix);
            ossEntity.setCreateBy(ShiroUtils.getLoginName());
            ossEntity.setFileName(fileName);
            ossEntity.setCreateTime(new Date());
            ossEntity.setService(storage.getService());
            ossEntity.setMd5(filemd5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(save(ossEntity));
    }

    /* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#findById(java.lang.Long)
     */
    @Override
    public SysOss findById(Long ossId)
    {
        return sysOssMapper.selectByPrimaryKey(ossId);
    }

    @Override
    public List<SysOss> findByMd5(String ossMd5) {
        Example example = new Example(SysOss.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("md5",ossMd5);
        return sysOssMapper.selectByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#update(com.ruoyi.system.domain.SysOss)
     */
    @Override
    public int update(SysOss sysOss)
    {
        return sysOssMapper.updateByPrimaryKeySelective(sysOss);
    }

    /* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysOssService#deleteByIds(java.lang.String)
     */
    @Override
    public int deleteByIds(String ids)
    {
        return sysOssMapper.deleteByIds(ids);
    }
}
