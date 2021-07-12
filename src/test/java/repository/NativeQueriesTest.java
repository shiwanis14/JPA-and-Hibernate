package repository;

import java.util.List;
import com.own.projects.jpademo.JpademoApplication;
import com.own.projects.jpademo.entity.Course;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//launch the spring boot context with this class
@SpringBootTest(classes = JpademoApplication.class)
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void contextLoads() {
		logger.info("Test running");
	}


	@Test
	public void findById_positionalParams() {
		Query nativeQuery = em.createNativeQuery("SELECT * FROM COURSE where id =?  ", Course.class);
		nativeQuery.setParameter(1,10001L);
		List result = nativeQuery.getResultList();
		logger.info("result is {}",result);
	}

	@Test
	public void findById_namedParams() {
		Query nativeQuery = em.createNativeQuery("SELECT * FROM COURSE where id = :id  ", Course.class);
		nativeQuery.setParameter("id",10001L);
		List result = nativeQuery.getResultList();
		logger.info("result is {}",result);
	}

	@Test
	@Transactional
	public void updateCourse() {
		Query nativeQuery = em.createNativeQuery("UPDATE COURSE set last_updated_date = sysdate() where id = ? ", Course.class);
		nativeQuery.setParameter(1,10001L);
		int rows= nativeQuery.executeUpdate();
		logger.info("result is {}",rows);
	}


}
