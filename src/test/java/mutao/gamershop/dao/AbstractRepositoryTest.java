package mutao.gamershop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractRepositoryTest {

	protected static EntityManagerFactory emFactory;

	protected static EntityManager em;

	/**
	 * Abrindo conexão com o Database, criando a EntityManager e abrindo a
	 * transação.
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emFactory = Persistence.createEntityManagerFactory("default");
		em = emFactory.createEntityManager();
		em.getTransaction().begin();
	}

	/**
	 * Comita a transação, encerra a EntityManager e a factory.
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	
}
