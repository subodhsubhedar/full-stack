package com.myapp.library.menu.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManager em;
	private static EntityManagerFactory emFactory;

	static {
		try {
			emFactory = Persistence.createEntityManagerFactory("library_jpa");
			em = emFactory.createEntityManager();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static EntityManager getEntityManager() {
		return em;
	}

	public static void shutdown() {
		em.close();
		emFactory.close();
	}

}
