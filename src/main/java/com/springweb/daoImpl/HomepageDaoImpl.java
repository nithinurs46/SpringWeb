package com.springweb.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.springweb.dao.HomepageDao;

@Repository
public class HomepageDaoImpl implements HomepageDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public String getHomepageCount() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("PROC_GET_COUNTS")
				.registerStoredProcedureParameter("p_param_1", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("p_param_2", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("p_param_out", String.class, ParameterMode.OUT)
				.setParameter("p_param_1", "A")
				.setParameter("p_param_2", "02");

		query.execute();
		String result = (String) query.getOutputParameterValue("p_param_out");
		//0~68~122~56~4~36~5~10~19~3~195~95~663~34~4~0
		return result;
	}
	
}
