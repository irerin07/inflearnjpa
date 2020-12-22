package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member m1 = new Member(9L, "aa");
            Member m2 = new Member(10L, "bb");

            em.persist(m1);
            em.persist(m2);

            Member member = em.find(Member.class, 9L);
            System.out.println("member.getName() = " + member.getName());

            System.out.println("===================");

            em.remove(m1);
            em.find(Member.class, 9L);
            System.out.println("member.getName() = " + member.getName());

            System.out.println("------------------");

            
            tx.commit();
            em.find(Member.class, 9L);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
