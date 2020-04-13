package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonField;
import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.user.api.dto.OrganizationDto;
import aaron.user.service.biz.dao.OrganizationDao;
import aaron.user.service.biz.service.OrganizationService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Organization;
import aaron.user.service.pojo.model.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, Organization> implements OrganizationService {
    @FullCommonField
    @Override
    public boolean save(OrganizationDto organizationDto) {
        Organization organization = CommonUtils.copyProperties(organizationDto,Organization.class);
        if (save(organization)){
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(OrganizationDto organizationDto) {
        Organization organization = CommonUtils.copyProperties(organizationDto,Organization.class);
        organization.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        if (updateById(organization)){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<OrganizationDto> list) {
        List<Long> ids = list.stream().map(OrganizationDto::getId).collect(Collectors.toList());
        if (removeByIds(ids)){
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    @Override
    public List<Organization> list(OrganizationDto organizationDto) {
        Organization organization = CommonUtils.copyProperties(organizationDto,Organization.class);
        return baseMapper.query(organization);
    }
}
