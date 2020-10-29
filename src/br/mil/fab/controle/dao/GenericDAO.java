package br.mil.fab.controle.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class GenericDAO<T> {
	private final static String UNIT_NAME = "controle";

	private final static int ASC = 0;

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	public void save(T entity) {
		em.persist(entity);
	}

	protected void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = em.getReference(classe, id);

		em.remove(entityToBeRemoved);
	}
	
	public void deletar(T entity) {
		em.remove(em.merge(entity));
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public T find(Object entityID) {
		return em.find(entityClass, entityID);
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	// Using the unchecked because JPA does not have a
	// ery.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected T findOneResult(String jpaQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = em.createQuery(jpaQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			List<T> results = query.getResultList();

			if (!results.isEmpty()) {
				result = (T) results.get(0);
			}

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	protected int countResult(String jpaQuery, Map<String, Object> parameters) {
		int count = 0;

		try {
			Query query = em.createQuery(jpaQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			count = Integer.parseInt("" + query.getSingleResult());

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findResults(String jpaQuery, Map<String, Object> parameters) {

		List<T> results = new ArrayList<T>();
		try {
			Query query = em.createQuery(jpaQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			results = query.getResultList();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return results;
	}
	
	 public void excluir(T entity) {
	        if (!em.contains(entity)) {
	            entity = em.merge(entity);
	        }

	        em.remove(entity);

	    }

	@SuppressWarnings("unchecked")
	protected List<T> findResultsPaginated(String jpaQuery, Map<String, Object> parameters, int startingAt,
			int maxPage) {

		List<T> results = new ArrayList<T>();
		try {
			Query query = em.createQuery(jpaQuery);
			query.setFirstResult(startingAt);
			query.setMaxResults(maxPage);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			results = query.getResultList();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findResultsCriteriaPaginated(Class<T> classe, Map<String, Object> parameters, int startingAt,
			int maxPage, Map<String, Integer> orderBy) {

		List<T> results = new ArrayList<T>();
		try {
			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<T> query = qb.createQuery(classe);
			Root<T> element = query.from(classe);

			if (orderBy != null && !orderBy.isEmpty()) {
				orderQueryCriteriaParameters(query, qb, element, orderBy);

			}
			if (parameters != null && !parameters.isEmpty()) {

				populateQueryCriteriaParameters(query, qb, element, parameters);
			}

			Query queryResult = em.createQuery(query);
			queryResult.setFirstResult(startingAt);
			queryResult.setMaxResults(maxPage);

			results = queryResult.getResultList();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return results;
	}

	protected Long findResultsCriteriaPaginatedCount(Class<T> classe, Map<String, Object> parameters) {

		long result = 0;
		try {
			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<Long> query = qb.createQuery(Long.class);
			Root<T> element = query.from(classe);
			query.select(qb.count(element));
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryCriteriaParametersCount(query, qb, element, parameters);
			}
			Query queryResult = em.createQuery(query);

			result = (Long) queryResult.getSingleResult();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	public void populateQueryParameters(Query query, Map<String, Object> parameters) {

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

	}

	public void populateQueryCriteriaParameters(CriteriaQuery<T> query, CriteriaBuilder qb, Root<T> element,
			Map<String, Object> parameters) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			predicates.add(qb.equal(element.get(entry.getKey()), entry.getValue()));
		}
		query.where(predicates.toArray(new Predicate[] {}));

	}

	private void populateQueryCriteriaParametersCount(CriteriaQuery<Long> query, CriteriaBuilder qb, Root<T> element,
			Map<String, Object> parameters) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			predicates.add(qb.equal(element.get(entry.getKey()), entry.getValue()));
		}
		query.where(predicates.toArray(new Predicate[] {}));

	}

	public void orderQueryCriteriaParameters(CriteriaQuery<T> query, CriteriaBuilder qb, Root<T> element,
			Map<String, Integer> orderBy) {

		for (Map.Entry<String, Integer> entry : orderBy.entrySet()) {
			switch (entry.getValue()) {
			case ASC:
				query.orderBy(qb.asc(element.get(entry.getKey())));
				break;
			default:
				query.orderBy(qb.desc(element.get(entry.getKey())));
				break;
			}

		}
	}

	protected Long findResultsCriteriaPaginatedCount(CriteriaBuilder qb, Root<T> element, CriteriaQuery<Long> query,
			Map<String, Object> parameters, List<Predicate> p) {

		long result = 0;
		try {

			populateQueryCriteriaParametersCount(query, qb, element, parameters, p);

			query.select(qb.count(element));

			Query queryResult = em.createQuery(query);

			result = (Long) queryResult.getSingleResult();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findResultsCriteriaPaginated(CriteriaBuilder qb, Root<T> element, CriteriaQuery<T> query,
			Map<String, Object> parameters, int startingAt, int maxPage, Map<String, Integer> orderBy,
			List<Predicate> p) {

		List<T> results = new ArrayList<T>();
		try {

			if (orderBy != null && !orderBy.isEmpty()) {
				orderQueryCriteriaParameters(query, qb, element, orderBy);

			}

			populateQueryCriteriaParameters(query, qb, element, parameters, p);

			Query queryResult = em.createQuery(query);
			queryResult.setFirstResult(startingAt);
			queryResult.setMaxResults(maxPage);

			results = queryResult.getResultList();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return results;
	}

	protected void populateQueryCriteriaParameters(CriteriaQuery<T> query, CriteriaBuilder qb, Root<T> element,
			Map<String, Object> parameters, List<Predicate> p) {

		if (parameters != null && !parameters.isEmpty()) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				p.add(qb.like(element.get(entry.getKey()), entry.getValue() + "%"));
			}
		}
		query.where(p.toArray(new Predicate[] {}));

	}

	protected void populateQueryCriteriaParametersCount(CriteriaQuery<Long> query, CriteriaBuilder qb, Root<T> element,
			Map<String, Object> parameters, List<Predicate> p) {

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			p.add(qb.like(element.get(entry.getKey()), entry.getValue() + "%"));
		}
		query.where(p.toArray(new Predicate[] {}));

	}
	
    protected void populateNativeQueryParameters(Query query, Map<String, Object> parameters) {
    	int i=1;
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(i++, entry.getValue());
           
        }

    }
	
    @SuppressWarnings("unchecked")
    protected List<T> findResultsNativeQueryPaginated(String sql, Map<String, Object> parameters, int startPage,int maxPage) {
       List<T> results = new ArrayList<T>();
       try {
           Query query = em.createNativeQuery(sql.toString(), this.entityClass);
           // Method that will populate parameters if they are passed not null and empty
           if (parameters != null && !parameters.isEmpty()) {
              populateNativeQueryParameters(query, parameters);
           }
		   // query.setFirstResult((startPage-1) * maxPage); 
		   query.setFirstResult(startPage);
		   
		   query.setMaxResults(maxPage);
           results = query.getResultList();
       } catch (Exception e) {
           System.out.println("Error while running query: " + e.getMessage());
           e.printStackTrace();
       }
       return results;
    }

    protected long findResultsNativeQueryPaginatedCount(String sql, Map<String, Object> parameters) {
    	long count = 0;
    	try {
            Query query = em.createNativeQuery(sql.toString());
            if (parameters != null && !parameters.isEmpty()) {
               populateNativeQueryParameters(query, parameters);
            }
            count =  Long.parseLong(query.getResultList().get(0)+"");
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
    
    protected long resultadoTotalCount(String sql) {
    	long count = 0;
    	try {
            Query query = em.createNativeQuery(sql.toString());
            Object o = query.getSingleResult(); 

            if (o.getClass().equals(BigDecimal.class) ){
                BigDecimal big = (BigDecimal) o;
                count = big.setScale(0,BigDecimal.ROUND_UP).longValueExact();
            }else{
            	count = (Long) o;
            }
              
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

}
