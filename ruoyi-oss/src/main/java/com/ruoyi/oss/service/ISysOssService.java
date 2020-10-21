package com.ruoyi.oss.service;



import com.ruoyi.oss.domain.SysOss;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传
 */
public interface ISysOssService
{
    /**
     * 列表查询方法
     * @param sysOss
     * @return
     * @author zmr
     */
    List<SysOss> getList(SysOss sysOss);

    /**
     * @param ossEntity
     * @author zmr
     */
    int save(SysOss ossEntity);

    /**
     *
     * @param file
     * @return hanzhenyong
     */
    String save(MultipartFile file);

    /**
     * @param ossId
     * @return
     * @author zmr
     */
    SysOss findById(Long ossId);

    /**
     *
     * @param ossMd5
     * @return
     */
    List<SysOss> findByMd5(String ossMd5);

    /**
     * @param sysOss
     * @return
     * @author zmr
     */
    int update(SysOss sysOss);

    /**
     * @param ids
     * @return
     * @author zmr
     */
    int deleteByIds(String ids);
}
