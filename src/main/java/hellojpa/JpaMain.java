package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            Member member = new Member();
//
//            member.setId(2L);
//            member.setName("HelloB");
            Member member = em.find(Member.class, 1L);
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getName() = " + member.getName());

//            em.remove(member);
            member.setName("HelloJPA");

//            em.persist(member); //update쿼리는 persist할 필요가 없다.

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}