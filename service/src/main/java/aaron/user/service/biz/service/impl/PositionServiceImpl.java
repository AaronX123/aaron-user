package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.PositionDto;
import aaron.user.service.biz.dao.PositionDao;
import aaron.user.service.biz.service.PositionService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Position;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionDao, Position> implements PositionService {
    @FullCommonFieldU
    @Override
    public boolean save(PositionDto positionDto) {
        Position position = CommonUtils.copyProperties(positionDto,Position.class);
        if (save(position)){
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(PositionDto positionDto) {
        Position position = CommonUtils.copyProperties(positionDto,Position.class);
        position.setJudgeId(CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId()) ? null : CommonUtils.judgeCompanyAndOrg());
        if (baseMapper.update(position) == 1){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<Position> positionList) {
        for (Position position : positionList) {
            position.setJudgeId(CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId()) ? null : CommonUtils.judgeCompanyAndOrg());
        }
        if (baseMapper.getLeafCount(positionList) > 0){
            throw new UserException(UserError.RECORD_IS_IN_USE);
        }
        if (baseMapper.deleteByIdList(positionList) == positionList.size()){
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    @Override
    public List<Position> list(Position position) {
        return baseMapper.query(position);
    }

    @Override
    public List<Position> listCompany() {
        return baseMapper.queryOptions();
    }
}
