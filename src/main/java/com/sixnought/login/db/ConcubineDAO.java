
// package com.sixnought.login.db;

// import com.sixnought.login.core.Concubine;
// import io.dropwizard.hibernate.AbstractDAO;
// import java.util.List;
// import java.util.Optional;
// import org.hibernate.SessionFactory;

// public class ConcubineDAO extends AbstractDAO<Concubine> {

//    public ConcubineDAO(SessionFactory sessionFactory) {
//         super(sessionFactory);
//     }

//     public List<Concubine> findByUserId(int id) {
//         return list(namedQuery("Concubine.findByUserId")
//                 .setParameter("id", id));
//     }

//     public Optional<Concubine> findById(int id) {
//         return Optional.ofNullable(get(id));
//     }

//     public Optional<Concubine> findByIdAndUserId(int id, int userId) {
//         return Optional.ofNullable(
//                 uniqueResult(
//                         namedQuery("Concubine.findByIdAndUserId")
//                         .setParameter("id", id)
//                         .setParameter("userId", userId)
//                 )
//         );
//     }

//     public Concubine save(Concubine bookmark) {
//         return persist(bookmark);
//     }

//     public void delete(Integer id) {
//         namedQuery("Concubine.remove")
//                 .setParameter("id", id)
//                 .executeUpdate();
//     }
// }
