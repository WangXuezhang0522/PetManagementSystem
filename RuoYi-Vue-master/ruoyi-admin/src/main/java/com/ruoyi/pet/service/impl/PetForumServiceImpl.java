package com.ruoyi.pet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetForumMapper;
import com.ruoyi.pet.domain.PetForum;
import com.ruoyi.pet.service.IPetForumService;

/**
 * 宠物论坛Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetForumServiceImpl implements IPetForumService 
{
    @Autowired
    private PetForumMapper petForumMapper;

    /**
     * 查询宠物论坛
     * 
     * @param id 宠物论坛主键
     * @return 宠物论坛
     */
    @Override
    public PetForum selectPetForumById(Long id)
    {
        return petForumMapper.selectPetForumById(id);
    }

    /**
     * 查询宠物论坛列表
     * 
     * @param petForum 宠物论坛
     * @return 宠物论坛
     */
    @Override
    public List<PetForum> selectPetForumList(PetForum petForum)
    {
        return petForumMapper.selectPetForumList(petForum);
    }

    /**
     * 新增宠物论坛
     * 
     * @param petForum 宠物论坛
     * @return 结果
     */
    @Override
    public int insertPetForum(PetForum petForum)
    {
        petForum.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        petForum.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
        petForum.setCreateTime(DateUtils.getNowDate());
        return petForumMapper.insertPetForum(petForum);
    }

    /**
     * 修改宠物论坛
     * 
     * @param petForum 宠物论坛
     * @return 结果
     */
    @Override
    public int updatePetForum(PetForum petForum)
    {
        petForum.setUpdateTime(DateUtils.getNowDate());
        return petForumMapper.updatePetForum(petForum);
    }

    /**
     * 批量删除宠物论坛
     * 
     * @param ids 需要删除的宠物论坛主键
     * @return 结果
     */
    @Override
    public int deletePetForumByIds(Long[] ids)
    {
        return petForumMapper.deletePetForumByIds(ids);
    }

    /**
     * 删除宠物论坛信息
     * 
     * @param id 宠物论坛主键
     * @return 结果
     */
    @Override
    public int deletePetForumById(Long id)
    {
        return petForumMapper.deletePetForumById(id);
    }
}
