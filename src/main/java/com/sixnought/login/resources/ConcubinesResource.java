// package com.sixnought.login.resources;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.google.common.base.Strings;
// import com.sixnought.login.core.Concubine;
// import com.sixnought.login.core.User;
// import com.sixnought.login.db.ConcubineDAO;
// import io.dropwizard.auth.Auth;
// import io.dropwizard.hibernate.UnitOfWork;
// import io.dropwizard.jersey.params.IntParam;
// import java.io.IOException;
// import java.lang.reflect.InvocationTargetException;
// import java.util.HashMap;
// import java.util.Iterator;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import javax.validation.Valid;
// import javax.validation.constraints.NotNull;
// import javax.ws.rs.Consumes;
// import javax.ws.rs.DELETE;
// import javax.ws.rs.GET;
// import javax.ws.rs.NotFoundException;
// import javax.ws.rs.POST;
// import javax.ws.rs.PUT;
// import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
// import javax.ws.rs.Produces;
// import javax.ws.rs.WebApplicationException;
// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;
// import org.apache.commons.beanutils.BeanUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Path("/concubines")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON)
// public class ConcubinesResource {

//     public static final String WRONG_BODY_DATA_FORMAT
//             = "Wrong body data format";
//     private static final Logger LOGGER
//             = LoggerFactory.getLogger(ConcubinesResource.class);
//     private final ConcubineDAO concubineDAO;

//     public ConcubinesResource(final ConcubineDAO concubineDAO) {
//         this.concubineDAO = concubineDAO;
//     }

//     @GET
//     @UnitOfWork
//     public List<Concubine> getConcubines(@Auth User user) {
//         return concubineDAO.findByUserId(user.getId());
//     }

//     @GET
//     @Path("/{id}")
//     @UnitOfWork
//     public Optional<Concubine> getConcubine(@PathParam("id") IntParam id,
//             @Auth User user) {
//         return concubineDAO.findByIdAndUserId(id.get(), user.getId());
//     }

//     @POST
//     @UnitOfWork
//     public Concubine addConcubine(@Valid @NotNull Concubine concubine,
//             @Auth User user) {

//         concubine.setUser(user);
//         return concubineDAO.save(concubine);
//     }

//     @PUT
//     @Path("/{id}")
//     @UnitOfWork
//     public Concubine modifyConcubine(@PathParam("id") IntParam id,
//             String jsonData,
//             @Auth User user) {

//         Concubine concubine = findConcubineOrTrowException(id, user);

//         ObjectMapper objectMapper = new ObjectMapper();
//         Map<String, String> changeMap = null;
//         try {
//             changeMap = objectMapper.readValue(jsonData, HashMap.class);
//             purgeMap(changeMap);
//             BeanUtils.populate(concubine, changeMap);
//             return concubineDAO.save(concubine);
//         } catch (IOException |
//                 IllegalAccessException |
//                 InvocationTargetException ex) {
//             LOGGER.warn(WRONG_BODY_DATA_FORMAT, ex);
//             throw new WebApplicationException(WRONG_BODY_DATA_FORMAT,
//                     ex,
//                     Response.Status.BAD_REQUEST);
//         } finally {
//             if (changeMap != null) {
//                 changeMap.clear();
//             }
//         }
//     }

//     @DELETE
//     @Path("/{id}")
//     @UnitOfWork
//     public Concubine deleteConcubine(@PathParam("id") IntParam id,
//             @Auth User user) {
//         Concubine concubine
//                 = findConcubineOrTrowException(id, user);
//         concubineDAO.delete(id.get());
//         return concubine;
//     }

//     protected void purgeMap(final Map<String, String> changeMap) {
//         changeMap.remove("id");
//         changeMap.entrySet().removeIf(
//                 entry -> Strings.isNullOrEmpty(entry.getValue())
//         );
//     }

//     private Concubine findConcubineOrTrowException(IntParam id,
//             @Auth User user) {
//         Concubine concubine = concubineDAO.findByIdAndUserId(
//                 id.get(), user.getId()
//         ).orElseThrow(()
//                 -> new NotFoundException("Bookmerk requested was not found."));
//         return concubine;
//     }

// }
