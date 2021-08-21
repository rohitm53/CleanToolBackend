package com.indiacleantool.cleantool.web.companymodules.companyservice.dao;

import com.indiacleantool.cleantool.dao.BaseDao;
import com.indiacleantool.cleantool.web.companymodules.companyservice.models.entity.CompanyServiceEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.StringJoiner;

@Repository
public class CompanyServiceDaoImpl extends BaseDao implements CompanyServiceDao {

    @Override
    public List<CompanyServiceEntity> findAllByCompanyCode(String companyCode) {

        StringJoiner query = new StringJoiner(" ");
        query.add("select * from company_service");
        query.add("where company_code = :company_code");

        Query nativeQuery = getEntityManager().createNativeQuery(query.toString() , CompanyServiceEntity.class);
        nativeQuery.setParameter("company_code",companyCode);

        return nativeQuery.getResultList();
    }

    @Override
    public void deleteCompanyService(List<Long> ids) {

        StringJoiner query = new StringJoiner(" ");
        query.add("delete from company_service where id = :id");

        Query nativeQuery = getEntityManager().createNativeQuery(query.toString());

        for(Long id : ids){
            nativeQuery.setParameter("id",id);
            nativeQuery.executeUpdate();
        }
    }
}
